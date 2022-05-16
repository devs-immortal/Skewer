package net.immortaldevs.skewer.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.immortaldevs.skewer.Skewer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class SkewerItemGroups {

    public static final ItemGroup COOKING = FabricItemGroupBuilder.build(
            Skewer.locate("cooking"),
            () -> new ItemStack(SkewerItems.PREP_TABLE_ITEM));
    public static final ItemGroup FOODS = FabricItemGroupBuilder.build(
            Skewer.locate("foods"),
            () -> new ItemStack(SkewerItems.WOODEN_SKEWER));

    public static final ItemGroup FARMING = FabricItemGroupBuilder.build(
            Skewer.locate("farming"),
            () -> new ItemStack(SkewerItems.GARLIC));
}
