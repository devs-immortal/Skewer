package net.immortaldevs.skewer.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.immortaldevs.skewer.Skewer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class SkewerItemGroups {
    public static final ItemGroup FOODS = FabricItemGroupBuilder.build(
            Skewer.locate("foods"),
            () -> new ItemStack(SkewerItems.BAMBOO_SKEWER));

    public static final ItemGroup CONDIMENTS = FabricItemGroupBuilder.build(
            Skewer.locate("condiments"),
            () -> new ItemStack(SkewerItems.AIOLI));
}
