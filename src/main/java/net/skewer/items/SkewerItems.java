package net.skewer.items;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class SkewerItems {


    private static <V extends Item> V register(String name, V item){
        return Registry.register(Registry.ITEM, name, item);
    }
}
