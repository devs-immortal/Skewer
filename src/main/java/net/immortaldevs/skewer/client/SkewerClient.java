package net.immortaldevs.skewer.client;

import net.fabricmc.api.ClientModInitializer;

public final class SkewerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SkewerComponentModels.init();
    }
}
