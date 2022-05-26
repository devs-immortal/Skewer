package net.immortaldevs.skewer.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.immortaldevs.skewer.block.SkewerBlocks;
import net.immortaldevs.skewer.blockentity.PrepTableEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.create;

public class SkewerBlockEntityTypes {
    public static final BlockEntityType<PrepTableEntity> PREP_TABLE_TYPE = add("prep_table", create(PrepTableEntity::new, SkewerBlocks.PREP_TABLE));
    public static final BlockEntityType<PreparationTableBlockEntity> PREPARATION_TABLE = add("preparation_table", create(PreparationTableBlockEntity::new, SkewerBlocks.PREPARATION_TABLE));

    public static void init() {
    }

    private static <T extends BlockEntity> BlockEntityType<T> add(String id, FabricBlockEntityTypeBuilder<T> builder) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, id, builder.build());
    }
}
