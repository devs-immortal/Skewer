package net.id.skewer.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.id.skewer.Skewer;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public class SkewerItemTags {
    public static final Tag<Item> SKEWERABLE = TagFactory.ITEM.create(Skewer.locate("skewerable"));

    public static void init() {}
}
