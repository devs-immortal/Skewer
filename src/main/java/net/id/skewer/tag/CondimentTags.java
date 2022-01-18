package net.id.skewer.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.id.skewer.Skewer;
import net.id.skewer.registry.SkewerRegistries;
import net.minecraft.tag.Tag;
import net.id.skewer.condiments.Condiment;

public class CondimentTags {
    public static final TagFactory<Condiment> TAG_FACTORY = TagFactory.of(SkewerRegistries.CONDIMENT.getKey(), "tags/condiments");

    public static final Tag<Condiment> SKEWERABLE = TAG_FACTORY.create(Skewer.locate("skewerable"));

    public static void init() {}
}
