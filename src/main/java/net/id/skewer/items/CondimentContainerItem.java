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
    private final Item emptyContainer;
    private final boolean edible;

    public CondimentContainerItem(Settings settings, @Nullable Condiment condiment, @Nullable Item emptyContainer, boolean edible) {
        super(condiment != null ? settings.food(condiment.getFoodComponent()) : settings);
        this.condiment = condiment;
        this.edible = edible;
        this.emptyContainer = emptyContainer;
    }

    public CondimentContainerItem(Settings settings, @Nullable Condiment condiment, @Nullable Item emptyContainer){
        this(settings, condiment, emptyContainer, false);
    }

    public CondimentContainerItem(Settings settings, @Nullable Condiment condiment) {
        this(settings, condiment, SkewerItems.EMPTY_DISH);
    }

    public Item getEmptyContainer(){
        return emptyContainer;
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
        if (this.isFood() && this.edible && user instanceof PlayerEntity player){
            if (condiment.getOnConsumed() != null) {
                condiment.getOnConsumed().accept(player, stack.getItem());
            }
            if (this.getEmptyContainer() != null){
                player.giveItemStack(this.emptyContainer.getDefaultStack());
            }
        }
        return super.finishUsing(stack, world, user);
    }
}
