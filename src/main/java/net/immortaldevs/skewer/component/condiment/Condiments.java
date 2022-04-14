package net.immortaldevs.skewer.component.condiment;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.skewer.component.SkewerComponents;
import net.immortaldevs.skewer.item.SkewerItems;
import net.minecraft.item.Item;

public final class Condiments {
    private static final Reference2ReferenceMap<Item, Component> CONDIMENTS =
            new Reference2ReferenceOpenHashMap<>();

    public static void register(Item item, Component component) {
        CONDIMENTS.put(item, component);
    }

    public static Component get(Item item) {
        return CONDIMENTS.get(item);
    }

    public static void init() {
        register(SkewerItems.KELP_GOO, SkewerComponents.KELP_GOO);
        register(SkewerItems.AIOLI, SkewerComponents.AIOLI);
        register(SkewerItems.CHILLI_SAUCE, SkewerComponents.CHILLI_SAUCE);
    }
}
