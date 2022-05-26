package net.immortaldevs.skewer.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf;
import static net.immortaldevs.skewer.Skewer.locate;

public class SkewerBlocks {
    public static final PrepTableBlock PREP_TABLE = add("prep_table", new PrepTableBlock(copyOf(Blocks.OAK_WOOD)));
    public static final PreparationTableBlock PREPARATION_TABLE = add("preparation_table", new PreparationTableBlock(copyOf(Blocks.OAK_WOOD)));
    public static final GarlicCropBlock GARLIC = add("garlic", new GarlicCropBlock(copyOf(Blocks.POTATOES)));

    public static void init() {
    }

    private static <T extends Block> T add(String id, T item) {
        return Registry.register(Registry.BLOCK, locate(id), item);
    }
}
