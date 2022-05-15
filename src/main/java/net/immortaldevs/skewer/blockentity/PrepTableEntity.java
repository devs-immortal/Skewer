package net.immortaldevs.skewer.blockentity;

import com.google.common.collect.ImmutableSet;
import net.id.incubus_core.be.IncubusBaseBE;
import net.id.incubus_core.util.InventoryWrapper;
import net.immortaldevs.skewer.component.SkeweredFoodComponent;
import net.immortaldevs.skewer.component.SkeweredFoodComponents;
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

import java.util.Set;

public class PrepTableEntity extends IncubusBaseBE implements InventoryWrapper {

    private static final Set<Item> VALID_BODIES;
    private final DefaultedList<ItemStack> ingredients = DefaultedList.ofSize(16, ItemStack.EMPTY);
    private ItemStack body = ItemStack.EMPTY;
    private ItemStack output = ItemStack.EMPTY;

    public PrepTableEntity(BlockPos pos, BlockState state) {
        super(SkewerBlockEntities.PREP_TABLE_TYPE, pos, state);
    }

    /**
     * Takes care of player interaction, stuff like making sure everything ends up in its respective inventory.
     */
    public ActionResult handleInteraction(ItemStack handStack, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(world == null)
            return ActionResult.PASS;

        var handItem = handStack.getItem();
        var random = world.getRandom();
        SkeweredFoodComponent component = (SkeweredFoodComponent) SkeweredFoodComponents.fromItem(handItem);

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

        // If there is nothing else to do, try to extract something from the table.
        else if(player.isSneaking()) {
            if(player.getInventory().insertStack(extractIngredient())) {
                world.playSound(null, pos, SoundEvents.ITEM_BOOK_PUT, SoundCategory.BLOCKS, random.nextFloat() / 2F + 0.55F, 0.6F + (random.nextFloat() / 3F));
                return swingAndSync();
            }
        }
        else if(handStack == ItemStack.EMPTY) {
            player.setStackInHand(hand, body);
            body = ItemStack.EMPTY;
            return swingAndSync();
        }

        return ActionResult.PASS;
    }

    private void assembleKebab() {

    }

    public boolean isIngredientsFull() {
        for (ItemStack ingredient : ingredients) {
            if(ingredient.isEmpty())
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
        if(!world.isClient())
            sync();
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
