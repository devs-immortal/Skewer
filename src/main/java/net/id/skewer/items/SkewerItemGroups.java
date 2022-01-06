package net.id.skewer.items;

import net.id.skewer.Skewer;
import net.id.skewer.condiments.Condiments;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder.build;

public class SkewerItemGroups {
    public static final ItemGroup SKEWER_FOODS = build(
            Skewer.locate("foods"),
            () -> {
                ItemStack stack = new ItemStack(SkewerItems.SKEWER);
                SkewerItem.forceAdd(stack,
                        Items.COOKED_BEEF, Items.COOKED_BEEF,
                        Items.COOKED_CHICKEN, Items.COOKED_CHICKEN,
                        Condiments.AIOLI);
                return stack;
            });

    public static final ItemGroup SKEWER_CONDIMENTS = build(
            Skewer.locate("condiments"),
            () -> new ItemStack(SkewerItems.AIOLI_DISH));

}
