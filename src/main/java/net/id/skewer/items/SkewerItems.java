package net.id.skewer.items;

import net.id.skewer.Skewer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.registry.Registry;
import net.id.skewer.condiments.Condiments;

import java.util.Arrays;
import java.util.function.Consumer;

public class SkewerItems {

    // skewers & other whole foods
    private static Settings foods() {return new Settings().group(SkewerItemGroups.SKEWER_FOODS);}
    public static final SkewerItem SKEWER = register("skewer", new SkewerItem(foods()));

    // condiments and such
    private static Settings condiments() {return new Settings().group(SkewerItemGroups.SKEWER_CONDIMENTS);}
    public static final CondimentContainerItem EMPTY_DISH = register("empty_dish", new CondimentContainerItem(condiments(), Condiments.EMPTY));
    public static final CondimentContainerItem AIOLI_DISH = register("aioli_dish", new CondimentContainerItem(condiments(), Condiments.AIOLI, EMPTY_DISH));
    public static final CondimentItem GARLIC_CLOVE = register("garlic_clove", new CondimentItem(condiments(), Condiments.GARLIC, true));



    private static <V extends Item> V register(String name, V item){
        return Registry.register(Registry.ITEM, Skewer.locate(name), item);
    }

    public static void init(){
        // nothing, lonely, very, very lonely
    }
}
