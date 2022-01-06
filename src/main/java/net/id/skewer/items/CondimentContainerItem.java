package net.id.skewer.items;

import net.minecraft.item.Item;
import net.id.skewer.condiments.Condiment;
import org.jetbrains.annotations.Nullable;

public class CondimentContainerItem extends Item{
    private final Condiment condiment;
    private final Item emptyContainer;

    public CondimentContainerItem(Settings settings, @Nullable Condiment condiment, Item emptyContainer) {
        super(condiment != null ? settings.food(condiment.getFoodComponent()) : settings);
        this.condiment = condiment;
        this.emptyContainer = emptyContainer;
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
}
