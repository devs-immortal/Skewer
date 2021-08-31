package net.id.skewer.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.id.skewer.condiments.Condiment;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CondimentContainerItem extends Item{
    private final Condiment condiment;
    private final boolean edible;

    public CondimentContainerItem(Settings settings, @Nullable Condiment condiment) {
        this(settings, condiment, false);
    }

    public CondimentContainerItem(Settings settings, @Nullable Condiment condiment, boolean edible) {
        super(condiment != null ? settings.food(condiment.getFoodComponent()) : settings);
        this.condiment = condiment;
        this.edible = edible;
    }

    @Nullable
    public Condiment getCondiment(){
        return condiment;
    }

    @Override
    public boolean isFood() {
        return edible;
    }

    @Nullable
    @Override
    public FoodComponent getFoodComponent() {
        return condiment.getFoodComponent();
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (this.isFood() && user instanceof PlayerEntity player){
            if (condiment.getOnConsumed() != null) {
                condiment.getOnConsumed().accept(player, stack.getItem());
            }
        }
        return super.finishUsing(stack, world, user);
    }
}
