package net.immortaldevs.skewer.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class SkewerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SkewerBlockEntityRendererFactories.init();
        SkewerComponentModels.init();
    }
}
