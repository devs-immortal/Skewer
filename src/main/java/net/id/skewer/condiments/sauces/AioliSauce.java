package net.id.skewer.condiments.sauces;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.id.skewer.items.SkewerItems;

import java.util.Collection;

public class AioliSauce extends Sauce {
    @Override
    public Item getBucketItem() {
        return SkewerItems.AIOLI_DISH;
    }

    @Override
    public Collection<StatusEffect> getEffects() {
        return null; // temporary
    }
}
