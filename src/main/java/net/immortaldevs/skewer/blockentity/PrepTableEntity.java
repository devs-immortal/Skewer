package net.immortaldevs.skewer.blockentity;

import com.google.common.collect.ImmutableSet;
import net.id.incubus_core.be.IncubusBaseBE;
import net.id.incubus_core.util.InventoryWrapper;
import net.immortaldevs.skewer.block.entity.SkewerBlockEntityTypes;
import net.immortaldevs.skewer.component.KebabComponent;
import net.immortaldevs.skewer.component.SkewerComponents;
import net.immortaldevs.skewer.component.SkeweredFoodComponent;
import net.immortaldevs.skewer.component.SkeweredFoodComponents;
import net.immortaldevs.skewer.item.SkewerItem;
import net.immortaldevs.skewer.item.SkewerItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrepTableEntity extends IncubusBaseBE implements InventoryWrapper {

    private static final Set<Item> VALID_BODIES;
    private final DefaultedList<ItemStack> ingredients = DefaultedList.ofSize(16, ItemStack.EMPTY);
    private ItemStack body = ItemStack.EMPTY;
    private ItemStack output = ItemStack.EMPTY;

    public PrepTableEntity(BlockPos pos, BlockState state) {
        super(SkewerBlockEntityTypes.PREP_TABLE_TYPE, pos, state);
    }

    /**
     * Takes care of player interaction, stuff like making sure everything ends up in its respective inventory.
     */
    public ActionResult handleInteraction(ItemStack handStack, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(world == null)
            return ActionResult.PASS;

        var handItem = handStack.getItem();
        var random = world.getRandom();
        SkeweredFoodComponent component = SkeweredFoodComponents.get(handItem);

        // Check if the player is holding an item used in building dynamic foods.
        if(VALID_BODIES.contains(handItem)) {

            if(body.isEmpty()) {
                body = handStack;
                player.setStackInHand(hand, ItemStack.EMPTY);
            }
            else if(body.getCount() < body.getMaxCount() && body.isItemEqual(handStack)) {
                var change = Math.min(handStack.getCount(), body.getMaxCount() - body.getCount());
                body.increment(change);
                handStack.decrement(change);
            }
            world.playSound(null, pos, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON, SoundCategory.BLOCKS, random.nextFloat() / 3F + 0.125F, 0.25F + (random.nextFloat() / 3F));
            world.playSound(null, pos, SoundEvents.ITEM_BOOK_PUT, SoundCategory.BLOCKS, random.nextFloat() / 2F + 0.55F, 0.6F + (random.nextFloat() / 3F));
            return swingAndSync();
        }

        // If not, check if the item is a food with a valid component and if it can be inserted.
        else if(component != null && !isIngredientsFull() && !player.isSneaking()) {
            if(insertIngredient(handStack)) {
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS,  random.nextFloat() / 4F + 0.1F, 0.4F + (random.nextFloat() / 2F));
                //world.playSound(null, pos, SoundEvents.ITEM_BOOK_PUT, SoundCategory.BLOCKS, random.nextFloat() / 3F + 0.25F, 0.75F + (random.nextFloat() / 2.5F));
                return swingAndSync();
            }
        }

        // Is the player holding a knife, perchance? time to makea some kebabbe
        else if(handItem == SkewerItems.KNIFE) {
            if(body.getItem() instanceof SkewerItem && !isIngredientsEmpty() && output.isEmpty())
                assembleKebab();

            world.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS,  random.nextFloat() / 4F + 0.1F, 0.4F + (random.nextFloat() / 2F));
            world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.BLOCKS,  random.nextFloat() / 3F + 0.2F, 0.85F + (random.nextFloat() / 2F));
            world.playSound(null, pos, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON, SoundCategory.BLOCKS, random.nextFloat() / 3F + 0.125F, 0.25F + (random.nextFloat() / 3F));
            return swingAndSync();
        }

        // If there is nothing else to do, try to extract something from the table.
        else if(player.isSneaking()) {
            if(player.getInventory().insertStack(extractIngredient())) {
                world.playSound(null, pos, SoundEvents.ITEM_BOOK_PUT, SoundCategory.BLOCKS, random.nextFloat() / 2F + 0.55F, 0.6F + (random.nextFloat() / 3F));
                return swingAndSync();
            }
        }
        else if(handStack == ItemStack.EMPTY) {
            if(!output.isEmpty()) {
                player.setStackInHand(hand, output);
                output = ItemStack.EMPTY;
                return swingAndSync();
            }
            else if(!body.isEmpty()) {
                player.setStackInHand(hand, body);
                body = ItemStack.EMPTY;
                return swingAndSync();
            }
        }

        return ActionResult.PASS;
    }

    /**
     * We makea the kebabbe
     */
    private void assembleKebab() {

        if(body.isEmpty())
            return;

        if(isIngredientsEmpty())
            return;

        SkewerItem skewer = (SkewerItem) body.getItem();
        int skewerCapacity = skewer.maxCapacity;
        int componentFill = 0;
        var components = new ArrayList<SkeweredFoodComponent>();

        for (int i = 0; i < ingredients.size(); i++) {
            var ingredient = ingredients.get(i);

            if(componentFill >= skewerCapacity)
                break;

            if(ingredient.isEmpty())
               continue;

            componentFill += 1;
            for (int slots = 0; slots < 1; slots++) {
                components.add(SkeweredFoodComponents.get(ingredient.getItem()));
            }
            ingredients.set(i, ItemStack.EMPTY);
        }

        float kebabOutput = 0;
        float kebabMultiplier = 1;
        List<Float> multipliers = new ArrayList<>();
        Set<SkeweredFoodComponent> usedComponents = new HashSet<>();

        for (SkeweredFoodComponent component : components) {
            kebabOutput += Math.max(0.5F, component.getHunger() / (component.getSaturationModifier() * 4) / (2 - skewerCapacity / 16F));

            var outputModifier = component.outputModifier;
            if(!usedComponents.contains(component)) {
                outputModifier *= 2F - skewerCapacity / 24F;
                usedComponents.add(component);
            }

            if(outputModifier > 0) {
                multipliers.add((float) outputModifier);
                if (multipliers.size() != 1) {
                    for (int i = 1; i < multipliers.size(); i++) {
                        outputModifier *= multipliers.get(i);
                    }
                }
                kebabMultiplier += outputModifier;
            }
        }

        output = new ItemStack(skewer, (int) Math.min(body.getCount(), Math.ceil(kebabOutput * kebabMultiplier)));
        var kebab = output.getOrCreateComponent("kebab", SkewerComponents.KEBAB);
        kebab.getOrCreateNbt().putDouble("posY", -0.0625);
        for (int i = 0; i < components.size() && i < skewerCapacity; i++) {
            KebabComponent.addFood(kebab, components.get(i));
        }

        body.decrement(output.getCount());
    }

    public boolean isIngredientsFull() {
        for (ItemStack ingredient : ingredients) {
            if(ingredient.isEmpty())
                return false;
        }
        return true;
    }

    public boolean isIngredientsEmpty() {
        for (ItemStack ingredient : ingredients) {
            if(!ingredient.isEmpty())
                return false;
        }
        return true;
    }

    public boolean insertIngredient(ItemStack stack) {
        for (int i = 0; i < ingredients.size(); i++) {
            if(ingredients.get(i).isEmpty()) {
                ingredients.set(i, new ItemStack(stack.getItem()));
                stack.decrement(1);
                return true;
            }
        }
        return false;
    }

    public ItemStack extractIngredient() {
        for (int i = ingredients.size() - 1; i >= 0; i--) {
            var ingredient = ingredients.get(i);
            if(!ingredient.isEmpty()) {
                ingredients.set(i, ItemStack.EMPTY);
                return ingredient;
            }
        }
        return ItemStack.EMPTY;
    }

    public ActionResult swingAndSync() {
        assert world != null;
        if(!world.isClient()) {
            sync();
            markDirty();
        }
        return ActionResult.success(world.isClient());
    }

    public ItemStack getBody() {
        return body;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public void save(NbtCompound nbt) {
        super.save(nbt);
        Inventories.writeNbt(nbt, ingredients);
        var bodyNbt = new NbtCompound();
        var outputNbt = new NbtCompound();
        body.writeNbt(bodyNbt);
        output.writeNbt(outputNbt);
        nbt.put("body", bodyNbt);
        nbt.put("output", outputNbt);
    }

    @Override
    public void load(NbtCompound nbt) {
        super.load(nbt);
        Inventories.readNbt(nbt, ingredients);
        body = ItemStack.fromNbt(nbt.getCompound("body"));
        output = ItemStack.fromNbt(nbt.getCompound("output"));
    }

    @Override
    public void saveClient(NbtCompound nbt) {
        save(nbt);
    }

    @Override
    public void loadClient(NbtCompound nbt) {
        load(nbt);
    }

    static {
        var builder = ImmutableSet.<Item>builder();
        builder.add(SkewerItems.WOODEN_SKEWER);
        builder.add(SkewerItems.COPPER_SKEWER);
        builder.add(SkewerItems.IRON_SKEWER);
        builder.add(SkewerItems.BAMBOO_SKEWER);
        VALID_BODIES = builder.build();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return ingredients;
    }
}
