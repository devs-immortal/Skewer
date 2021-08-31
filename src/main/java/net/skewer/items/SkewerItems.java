package net.skewer.items;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.registry.Registry;
import net.skewer.Skewer;
import net.skewer.condiments.Condiments;

public class SkewerItems {
    public static final CondimentContainer AIOLI_DISH = register("aioli_dish", new CondimentContainer(new Settings(), Condiments.AIOLI_SAUCE));
    public static final CondimentContainer EMPTY_DISH = register("empty_dish", new CondimentContainer(new Settings(), null));

    private static <V extends Item> V register(String name, V item){
        return Registry.register(Registry.ITEM, Skewer.locate(name), item);
    }

    public static void init(){
        // nothing, lonely, very very lonely
    }
}
