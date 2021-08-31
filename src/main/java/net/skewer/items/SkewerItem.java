package net.skewer.items;

import net.minecraft.item.Item;
import net.skewer.tag.CondimentTags;
import net.skewer.tag.SkewerItemTags;
import net.skewer.condiments.Condiment;

public class SkewerItem extends FoodStackItem {
    public SkewerItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canBeAdded(Item item){
        return SkewerItemTags.SKEWERABLE.contains(item);
    }

    @Override
    protected boolean canBeAdded(Condiment condiment) {
        return CondimentTags.SKEWERABLE.contains(condiment);
    }
}
