package net.id.skewer.items;

import com.mojang.datafixers.util.Pair;
import net.id.skewer.registry.SkewerRegistries;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.id.skewer.condiments.Condiment;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// TODO rework
public abstract class MultiFoodItem extends Item {

    public MultiFoodItem(Settings settings) {
        super(settings);
    }

    public static boolean add(ItemStack foodStack, Item addition, int amount){
        MultiFoodItem item = (MultiFoodItem) foodStack.getItem();
        if (!item.canBeAdded(addition)) {
            return false;
        }
        forceAdd(foodStack, addition, amount);
        return true;
    }

    public static boolean add(ItemStack foodStack, Condiment addition, int amount){
        MultiFoodItem item = (MultiFoodItem) foodStack.getItem();
        if (!item.canBeAdded(addition)) {
            return false;
        }
        forceAdd(foodStack, addition, amount);
        return true;
    }

    protected abstract boolean canBeAdded(Item item);

    protected abstract boolean canBeAdded(Condiment condiment);

    @Nullable
    @Override
    public FoodComponent getFoodComponent() {
        return SkewerFoodComponents.EMPTY;
    }

    @Override
    public boolean isFood(){
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (getFoodList(itemStack).size() == 0){
            return TypedActionResult.pass(itemStack);
        }

        // The rest of this method is normal item behavior
        if (user.canConsume(this.getFoodComponent().isAlwaysEdible())) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        world.emitGameEvent(user, GameEvent.EAT, user.getCameraBlockPos());
        for (Item food : getFoodList(stack)) {
            if (!food.isFood()) continue;
            ItemStack returnStack = food.finishUsing(food.getDefaultStack(), world, user);
            if (user instanceof PlayerEntity player) {
                if (!player.getAbilities().creativeMode && !returnStack.getItem().equals(Items.AIR)) {
                    player.giveItemStack(returnStack);
                }
            }
        }
        // TODO: verify this works
        for (Condiment condiment : getCondimentList(stack)) {
            var list = condiment.getFoodComponent().getStatusEffects();

            for (Pair<StatusEffectInstance, Float> pair : list) {
                if (!world.isClient && pair.getFirst() != null && world.random.nextFloat() < pair.getSecond()) {
                    user.addStatusEffect(new StatusEffectInstance(pair.getFirst()));
                }
            }
        }

        user.emitGameEvent(GameEvent.EAT);
        return super.finishUsing(stack, world, user);
    }

    public static List<Item> getFoodList(ItemStack stack){
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("skewerfoods")){
            nbt.put("skewerfoods", new NbtList());
        }
        NbtList foods = nbt.getList("skewerfoods", NbtList.STRING_TYPE);
        try {
            return foods.stream().map(element -> {
                Identifier id = new Identifier(element.asString());
                return Registry.ITEM.get(id);
            }).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public static List<Condiment> getCondimentList(ItemStack stack){
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("skewercondiments")){
            nbt.put("skewercondiments", new NbtList());
        }
        NbtList condiments = nbt.getList("skewercondiments", NbtList.STRING_TYPE);
        try {
            return condiments.stream().map(element -> {
                Identifier id = new Identifier(element.asString());
                return SkewerRegistries.CONDIMENT.get(id);
            }).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    protected static void forceAdd(ItemStack stack, Item item, int amount){
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("skewerfoods")) {
            nbt.put("skewerfoods", new NbtList());
        }
        NbtList foods = nbt.getList("skewerfoods", NbtList.STRING_TYPE);
        for(int i = 0; i < amount; i++) {
            foods.add(NbtString.of(Registry.ITEM.getId(item).toString()));
        }
    }

    protected static void forceAdd(ItemStack stack, Condiment condiment, int amount){
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains("skewercondiments")) {
            nbt.put("skewercondiments", new NbtList());
        }
        NbtList condiments = nbt.getList("skewercondiments", NbtList.STRING_TYPE);
        try {
            for (int i = 0; i < amount; i++) {
                condiments.add(NbtString.of(SkewerRegistries.CONDIMENT.getId(condiment).toString()));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // For ease of use, particularly before tags have been initialized
    protected static void forceAdd(ItemStack stack, Object... additions){
        for (var addition : additions) {
            if (addition instanceof Item item) {
                forceAdd(stack, item, 1);
            } else if (addition instanceof Condiment cond) {
                forceAdd(stack, cond, 1);
            }
        }
    }
}
