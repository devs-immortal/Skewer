package net.id.skewer.tag;

import net.id.skewer.Skewer;
import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.RequiredTagListRegistry;
import net.minecraft.tag.Tag.Identified;
import net.id.skewer.condiments.Condiment;
import net.id.skewer.condiments.Condiments;
import net.minecraft.util.Identifier;

public class CondimentTags {
    protected static final RequiredTagList<Condiment> REQUIRED_TAGS = RequiredTagListRegistry.register(Condiments.CONDIMENT.getKey(), "tags/condiments");

    public static final Identified<Condiment> SKEWERABLE = register(Skewer.locate("skewerable"));

    private static Identified<Condiment> register(Identifier id){
        return REQUIRED_TAGS.add(id.toString());
    }

}
