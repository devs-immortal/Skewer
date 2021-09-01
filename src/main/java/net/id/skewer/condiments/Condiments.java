package net.id.skewer.condiments;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.id.skewer.Skewer;
import net.id.skewer.items.SkewerFoodComponents;
import net.id.skewer.items.SkewerItems;
import net.minecraft.util.registry.Registry;

public class Condiments {
    public static final Registry<Condiment> CONDIMENT = FabricRegistryBuilder.createSimple(Condiment.class, Skewer.locate("condiment")).buildAndRegister();

    public static final Spice EMPTY = register("empty", new Spice(null));
    public static final Sauce AIOLI = register("aioli", new Sauce(SkewerFoodComponents.AIOLI, SkewerItems.AIOLI_DISH));
    public static final Spice GARLIC = register("garlic", new Spice(SkewerFoodComponents.GARLIC));

    private static <V extends Condiment> V register(String name, V sauce){
        return Registry.register(CONDIMENT, Skewer.locate(name), sauce);
    }

    public static void init(){
        // nothing here either;
    }
}
