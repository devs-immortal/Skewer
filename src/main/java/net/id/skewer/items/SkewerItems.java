package net.id.skewer.items;

import net.id.skewer.Skewer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.id.skewer.condiments.Condiments;

import java.util.Arrays;
import java.util.function.Consumer;

public class SkewerItems {

    public static Settings foods() {return new Settings().group(SkewerItemGroups.SKEWER_FOODS);}
    public static Settings condiments() {return new Settings().group(SkewerItemGroups.SKEWER_CONDIMENTS);}
    public static SkewerItem skewer() {return new SkewerItem(foods());}
    public static final SkewerItem SKEWER = register("skewer", skewer());
    public static final CondimentContainerItem EMPTY_DISH = register("empty_dish", new CondimentContainerItem(condiments(), Condiments.EMPTY));
    public static final CondimentContainerItem AIOLI_DISH = register("aioli_dish", new CondimentContainerItem(condiments(), Condiments.AIOLI, EMPTY_DISH));
    public static final CondimentContainerItem GARLIC_CLOVE = register("garlic_clove", new CondimentContainerItem(condiments(), Condiments.GARLIC, null, true));

    @SafeVarargs
    private static <V extends Item> V register(String name, V item, Consumer<Item>... also){
        Arrays.stream(also).forEach((thing) -> thing.accept(item));
        return Registry.register(Registry.ITEM, Skewer.locate(name), item);
    }

    public static void init(){
        // nothing, lonely, very very lonely
    }
}
