package net.immortaldevs.skewer.blockentity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.immortaldevs.skewer.Skewer;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.block.SkewerBlocks.*;

public class SkewerBlockEntities {

    public static final BlockEntityType<PrepTableEntity> PREP_TABLE_TYPE = add("prep_table", create(PrepTableEntity::new, PREP_TABLE));

    public static void init() {}

    public static <T extends BlockEntity> BlockEntityType<T> create(FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(factory, blocks).build();
    }

    public static <B extends BlockEntityType<?>> B add(String name, B blockEntity) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, Skewer.locate(name), blockEntity);
    }
}
