package net.immortaldevs.skewer.tag;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.Skewer.locate;

public final class SkewerItemTags {
    public static final TagKey<Item> SKEWERS = TagKey.of(Registry.ITEM_KEY, locate("skewers"));
}
