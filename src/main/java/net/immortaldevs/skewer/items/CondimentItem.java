package net.immortaldevs.skewer.items;

import net.immortaldevs.skewer.condiments.Condiment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CondimentItem extends Item{
    private final Condiment condiment;
    private final boolean edible;

    public CondimentItem(Settings settings, Condiment condiment, boolean edible) {
        super(settings);
        this.condiment = condiment;
        this.edible = edible;
    }

    @Override
    public FoodComponent getFoodComponent() {
        return condiment.getFoodComponent();
    }

    @Override
    public boolean isFood() {
        return edible;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (this.isFood() && user instanceof PlayerEntity player){
            condiment.getOnConsumed().accept(player, stack.getItem());
        }
        return super.finishUsing(stack, world, user);
    }

}
