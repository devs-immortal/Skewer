package net.immortaldevs.skewer.component;

import net.immortaldevs.sar.api.SarRegistries;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.Skewer.locate;

public final class SkewerComponents {
    public static final KebabComponent KEBAB = new KebabComponent();

    public static void init() {
        Registry.register(SarRegistries.COMPONENT, locate("skewer"), KEBAB);
        SkeweredFoodComponents.init();
        SkewerCondimentComponents.init();
    }

}
