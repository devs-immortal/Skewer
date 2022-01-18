package net.immortaldevs.skewer.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.immortaldevs.skewer.Skewer;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public class SkewerItemTags {
    public static final Tag<Item> SKEWERABLE = TagFactory.ITEM.create(Skewer.locate("skewerable"));

    public static void init() {}
}
