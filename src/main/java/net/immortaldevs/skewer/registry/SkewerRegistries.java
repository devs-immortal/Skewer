package net.immortaldevs.skewer.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.immortaldevs.skewer.condiments.Condiment;
import net.immortaldevs.skewer.Skewer;
import net.minecraft.util.registry.*;

public class SkewerRegistries {
    public static final Registry<Condiment> CONDIMENT = FabricRegistryBuilder.createDefaulted(Condiment.class, Skewer.locate("condition"), Skewer.locate("empty")).buildAndRegister();
}
