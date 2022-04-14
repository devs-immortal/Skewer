package net.immortaldevs.skewer.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.immortaldevs.skewer.Skewer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class SkewerItemGroups {
    public static final ItemGroup CONDIMENTS = FabricItemGroupBuilder.build(
            Skewer.id("condiments"),
            () -> new ItemStack(SkewerItems.AIOLI));
}
