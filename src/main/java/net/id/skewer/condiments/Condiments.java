package net.id.skewer.condiments;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.id.skewer.Skewer;
import net.minecraft.util.registry.Registry;
import net.id.skewer.condiments.sauces.AioliSauce;

public class Condiments {
    public static final Registry<Condiment> CONDIMENT = FabricRegistryBuilder.createSimple(Condiment.class, Skewer.locate("condiment")).buildAndRegister();

    public static Condiment AIOLI_SAUCE = register("aioli", new AioliSauce());

    private static <V extends Condiment> V register(String name, V sauce){
        return Registry.register(CONDIMENT, Skewer.locate(name), sauce);
    }

    public static void init(){
        // nothing here either;
    }
}
