package net.id.skewer.sar;

import net.immortaldevs.sar.base.SarRegistries;
import net.minecraft.util.registry.Registry;

import static net.id.skewer.Skewer.locate;

public class SkewerComponents {
    public static final SkewerComponent SKEWER = new SkewerComponent();

    public static void init() {
        Registry.register(SarRegistries.COMPONENT, locate("skewer"), SKEWER);
    }
}
