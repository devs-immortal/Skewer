package net.immortaldevs.skewer.condiments;

import net.immortaldevs.skewer.Skewer;
import net.immortaldevs.skewer.items.SkewerFoodComponents;
import net.immortaldevs.skewer.registry.SkewerRegistries;
import net.minecraft.util.registry.Registry;

public class Condiments {

    public static final SimpleCondiment EMPTY = register("empty", new SimpleCondiment(SkewerFoodComponents.EMPTY));
    public static final SimpleCondiment AIOLI = register("aioli", new SimpleCondiment(SkewerFoodComponents.AIOLI));
    public static final SimpleCondiment GARLIC = register("garlic", new SimpleCondiment(SkewerFoodComponents.GARLIC));

    private static <V extends Condiment> V register(String name, V sauce){
        return Registry.register(SkewerRegistries.CONDIMENT, Skewer.locate(name), sauce);
    }

    public static void init(){
        // nothing here either;
    }
}
