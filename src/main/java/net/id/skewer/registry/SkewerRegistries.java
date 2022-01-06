package net.id.skewer.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.id.skewer.condiments.Condiment;
import net.minecraft.util.registry.*;

import static net.id.skewer.Skewer.locate;

public class SkewerRegistries {
    public static final Registry<Condiment> CONDIMENT = FabricRegistryBuilder.createDefaulted(Condiment.class, locate("condition"), locate("empty")).buildAndRegister();
}
