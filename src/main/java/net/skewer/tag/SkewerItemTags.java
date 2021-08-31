package net.skewer.tag;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.skewer.Skewer;

public class SkewerItemTags {
    public static final Tag<Item> SKEWERABLE = TagRegistry.item(Skewer.locate("skewerable"));
}
