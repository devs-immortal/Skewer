package net.skewer.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.skewer.condiments.Condiment;

import java.util.Collection;

public abstract class FoodStackItem extends Item {
    public Collection<Item> FOODS;
    public Collection<Condiment> CONDIMENTS;

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

    public void addFood(Item item){
        FOODS.add(item);
    }

    public ItemStack addCondiment(ItemStack stack, PlayerEntity player){
        Condiment condiment;
        if (stack.getItem() instanceof CondimentContainer) {
            condiment = ((CondimentContainer) stack.getItem().asItem()).getCondiment();
        } else {
            return stack;
        }
        if (canBeAdded(condiment)) {
            if (!player.isCreative()){
                stack.decrement(1);
            }
            CONDIMENTS.add(condiment);
        }
        return stack;
    }

    public void addCondiment(Condiment condiment){
        CONDIMENTS.add(condiment);
    }

    protected abstract boolean canBeAdded(Item item);

    protected abstract boolean canBeAdded(Condiment condiment);
}
