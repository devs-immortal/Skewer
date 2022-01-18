package net.immortaldevs.skewer.items;

import net.immortaldevs.skewer.registry.SkewerRegistryQueues;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.immortaldevs.skewer.condiments.Condiments;
import net.minecraft.item.ItemGroup;

import static net.id.incubus_core.util.RegistryQueue.Action;

public class SkewerItems {

    // skewers & other whole foods
    private static Settings foods() {return new Settings().group(SkewerItemGroups.SKEWER_FOODS);}
    public static final SkewerItem SKEWER = add("skewer", new SkewerItem(foods().group(ItemGroup.FOOD).food(new FoodComponent.Builder().build()).maxCount(1)));

    // condiments and such
    private static Settings condiments() {return new Settings().group(SkewerItemGroups.SKEWER_CONDIMENTS);}
    public static final CondimentContainerItem EMPTY_DISH = add("empty_dish", new CondimentContainerItem(condiments(), Condiments.EMPTY));
    public static final CondimentContainerItem AIOLI_DISH = add("aioli_dish", new CondimentContainerItem(condiments(), Condiments.AIOLI, EMPTY_DISH));
    public static final CondimentItem GARLIC_CLOVE = add("garlic_clove", new CondimentItem(condiments(), Condiments.GARLIC, true));

    @SafeVarargs
    private static <V extends Item> V add(String name, V item, Action<V>... actions){
        return SkewerRegistryQueues.ITEMS.add(name, item, actions);
    }

    public static void init(){
        SkewerRegistryQueues.ITEMS.register();
    }
}
