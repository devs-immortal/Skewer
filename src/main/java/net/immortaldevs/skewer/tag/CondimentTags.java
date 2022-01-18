package net.immortaldevs.skewer.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.immortaldevs.skewer.Skewer;
import net.immortaldevs.skewer.registry.SkewerRegistries;
import net.minecraft.tag.Tag;
import net.immortaldevs.skewer.condiments.Condiment;

public class CondimentTags {
    public static final TagFactory<Condiment> TAG_FACTORY = TagFactory.of(SkewerRegistries.CONDIMENT.getKey(), "tags/condiments");

    public static final Tag<Condiment> SKEWERABLE = TAG_FACTORY.create(Skewer.locate("skewerable"));

    public static void init() {}
}
