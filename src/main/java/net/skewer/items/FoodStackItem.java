package net.skewer.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.Collection;

public abstract class FoodStackItem extends Item {
    public Collection<Item> FOODS; // and sauces, which are foods.

    public FoodStackItem(Settings settings) {
        super(settings);
    }

    public ItemStack addFoodItem(ItemStack stack, PlayerEntity player){
        if (canBeAdded(stack.getItem())){
            if (!player.isCreative()){
                stack.decrement(1);
            }
            FOODS.add(stack.getItem());
        }
        return stack;
    }

    protected boolean canBeAdded(Item item) {
        return true;
    }
}
