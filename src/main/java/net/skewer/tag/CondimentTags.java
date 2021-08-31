package net.skewer.tag;

import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.RequiredTagListRegistry;
import net.minecraft.tag.Tag.Identified;
import net.skewer.condiments.Condiment;
import net.skewer.condiments.Condiments;

public class CondimentTags {
    protected static final RequiredTagList<Condiment> REQUIRED_TAGS = RequiredTagListRegistry.register(Condiments.CONDIMENT.getKey(), "tags/condiments");

    public static final Identified<Condiment> SKEWERABLE = register("skewerable");

    private static Identified<Condiment> register(String id){
        return REQUIRED_TAGS.add(id);
    }
}
