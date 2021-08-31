package net.id.skewer.items;

import net.id.skewer.Skewer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.registry.Registry;
import net.id.skewer.condiments.Condiments;

public class SkewerItems {
    public static final CondimentContainerItem AIOLI_DISH = register("aioli_dish", new CondimentContainerItem(new Settings(), Condiments.AIOLI));
    public static final CondimentContainerItem GARLIC_CLOVE = register("garlic_clove", new CondimentContainerItem(new Settings(), Condiments.GARLIC, true));
    public static final CondimentContainerItem EMPTY_DISH = register("empty_dish", new CondimentContainerItem(new Settings(), Condiments.EMPTY));

    private static <V extends Item> V register(String name, V item){
        return Registry.register(Registry.ITEM, Skewer.locate(name), item);
    }

    public static void init(){
        // nothing, lonely, very very lonely
    }
}
