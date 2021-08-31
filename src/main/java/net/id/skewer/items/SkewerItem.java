package net.id.skewer.items;

import net.minecraft.item.Item;
import net.id.skewer.tag.CondimentTags;
import net.id.skewer.tag.SkewerItemTags;
import net.id.skewer.condiments.Condiment;

public class SkewerItem extends FoodStackItem {
    public SkewerItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isFood(){
        return true;
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
