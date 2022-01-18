package net.immortaldevs.skewer.items;

import net.immortaldevs.skewer.Skewer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder.build;

public class SkewerItemGroups {
    public static final ItemGroup SKEWER_FOODS = build(
            Skewer.locate("foods"),
            () -> {
                ItemStack stack = new ItemStack(SkewerItems.SKEWER);
                /* TODO SkewerItem.forceAdd(stack,
                        Items.COOKED_BEEF, Items.COOKED_BEEF,
                        Items.COOKED_CHICKEN, Items.COOKED_CHICKEN,
                        Condiments.AIOLI);*/
                return stack;
            });

    public static final ItemGroup SKEWER_CONDIMENTS = build(
            Skewer.locate("condiments"),
            () -> new ItemStack(SkewerItems.AIOLI_DISH));

}
