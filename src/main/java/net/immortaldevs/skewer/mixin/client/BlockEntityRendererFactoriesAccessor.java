package net.immortaldevs.skewer.mixin.client;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockEntityRendererFactories.class)
public interface BlockEntityRendererFactoriesAccessor {
    @Invoker
    static <T extends BlockEntity> void callRegister(BlockEntityType<? extends T> type, BlockEntityRendererFactory<T> factory) {
        throw new Error();
    }
}
