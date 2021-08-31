package net.id.skewer.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.id.skewer.condiments.Condiment;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;

public abstract class FoodStackItem extends Item {
    public Collection<Item> FOODS = new ArrayList<>();
    public Collection<Condiment> CONDIMENTS = new ArrayList<>();

    public FoodStackItem(Settings settings) {
        super(settings);
    }

    public final FoodStackItem with(ItemStack stack, PlayerEntity player){
        Condiment condiment;
        if (stack.getItem() instanceof CondimentContainerItem container) {
            condiment = container.getCondiment();
            if (canBeAdded(condiment)) {
                if (!player.isCreative()){
                    stack.decrement(1);
                    if (container.getEmptyContainer() != null) {
                        player.giveItemStack(container.getEmptyContainer().getDefaultStack());
                    }
                }
                CONDIMENTS.add(condiment);
            }
        } else {
            if (canBeAdded(stack.getItem())){
                if (!player.isCreative()){
                    stack.decrement(1);
                }
                FOODS.add(stack.getItem());
            }
        }
        return this;
    }

    public <V> FoodStackItem with(V addition){
        if (addition instanceof Item item) {
            FOODS.add(item);
        } else if (addition instanceof Condiment condiment) {
            CONDIMENTS.add(condiment);
        }
        return this;
    }

    protected abstract boolean canBeAdded(Item item);

    protected abstract boolean canBeAdded(Condiment condiment);


    // TODO: this
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        FOODS.forEach((food) -> user.eatFood(world, food.getDefaultStack()));
        CONDIMENTS.forEach((condiment) -> {
            /*
                Det Condiments FoodComponent things to work
             */
        });

        CONDIMENTS.forEach(Condiment::getOnConsumed);

        FOODS.clear();
        CONDIMENTS.clear();


        return stack;
    }
}
