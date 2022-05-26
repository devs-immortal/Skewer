package net.immortaldevs.skewer.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.immortaldevs.skewer.block.entity.SkewerBlockEntityTypes;
import net.immortaldevs.skewer.mixin.client.BlockEntityRendererFactoriesAccessor;

@Environment(EnvType.CLIENT)
public final class SkewerBlockEntityRendererFactories {
    public static void init() {
        BlockEntityRendererFactoriesAccessor.callRegister(SkewerBlockEntityTypes.PREPARATION_TABLE,
                PreparationTableBlockEntityRenderer::new);
    }
}
