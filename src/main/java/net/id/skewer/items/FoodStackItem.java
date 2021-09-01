package net.id.skewer.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.id.skewer.condiments.Condiment;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import java.util.List;

public abstract class FoodStackItem extends Item {

    public FoodStackItem(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("unchecked")
    public static <V> ItemStack with(ItemStack foodStack, V addition, PlayerEntity player, int amount){
        if (addition instanceof ItemStack stack) {
            boolean success;
            success = tryAdd(foodStack, stack.getItem(), amount);
            if (success && !player.isCreative()) {
                stack.decrement(1);
                if (stack.getItem() instanceof CondimentContainerItem cci && cci.getEmptyContainer() != null){
                    player.giveItemStack(cci.getEmptyContainer().getDefaultStack());
                }
            }
        } else {
            return with(foodStack, addition, amount);
        }
        return foodStack;
    }

    @SuppressWarnings("unchecked")
    public static <V> ItemStack with(ItemStack foodStack, V addition, int amount){
        if (addition instanceof Item|| addition instanceof Condiment) {
            tryAdd(foodStack, addition, amount);
        }
        return foodStack;
    }

    // Only for use before tags are bound
    @SuppressWarnings("unchecked")
    public static <V> ItemStack withUnsafe(ItemStack foodStack, V addition, int amount){
        for (int i = 0; i < amount; i++) {
            if (addition instanceof Item item) {
                addFood(foodStack, item);
            } else if (addition instanceof Condiment condiment) {
                addCondiment(foodStack, condiment);
            }
        }
        return foodStack;
    }

    protected abstract boolean canBeAdded(Item item);

    protected abstract boolean canBeAdded(Condiment condiment);

    protected static <V> boolean tryAdd(ItemStack foodStack, V addition, int amount){
        assert foodStack.getItem() instanceof FoodStackItem;
        FoodStackItem item = (FoodStackItem) foodStack.getItem();
        if (addition instanceof Condiment condiment){
            return item.canBeAdded(condiment) && addCondiment(foodStack, condiment);
        } else if (addition instanceof CondimentContainerItem cci){
            Condiment condiment = cci.getCondiment();
            return item.canBeAdded(condiment) && addCondiment(foodStack, condiment);
        } else if (addition instanceof Item food){
            return item.canBeAdded(food) && addFood(foodStack, food);
        }
        return false;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        for (Item food : getFoodList(stack)) {
            if (!food.isFood()) continue;
            ItemStack returnStack = food.finishUsing(food.getDefaultStack(), world, user);
            if (user instanceof PlayerEntity player) {
                if (!player.getAbilities().creativeMode && !returnStack.getItem().equals(Items.AIR)) {
                    player.giveItemStack(returnStack);
                }
            }
            user.eatFood(world, food.getDefaultStack());
        }
        // TODO: do this for Condiments as well
        return super.finishUsing(stack, world, user);
    }

    // TODO: get a list going on with nbt
    public static List<Item> getFoodList(ItemStack stack){
        return null;
    }

    public static List<Condiment> getCondimentList(ItemStack stack){
        return null;
    }

    protected static boolean addFood(ItemStack stack, Item item){
        return false;
    }

    protected static boolean addCondiment(ItemStack stack, Condiment condiment){
        return false;
    }
}
