package net.immortaldevs.skewer.component;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import net.immortaldevs.sar.api.Component;
import net.immortaldevs.sar.api.SarRegistries;
import net.immortaldevs.sar.base.HungerModifier;
import net.immortaldevs.sar.base.SaturationModifierModifier;
import net.immortaldevs.sar.base.SimpleComponent;
import net.immortaldevs.skewer.item.SkewerItems;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.Skewer.locate;

public class SkewerCondimentComponents {
    private static final Reference2ReferenceMap<Item, Component> CONDIMENTS =
            new Reference2ReferenceOpenHashMap<>();

    public static final Component KELP_GOO = addCondiment("kelp_goo", SkewerItems.KELP_GOO, new Component());
    public static final SimpleComponent AIOLI = addCondiment("aioli", SkewerItems.AIOLI, new SimpleComponent(HungerModifier.add(2), SaturationModifierModifier.multiply(1.2f)));
    // Is this spelling on purpose?

    // What spelling?
    public static final SimpleComponent CHILLI_SAUCE = addCondiment("chilli_sauce", SkewerItems.CHILLI_SAUCE, new SimpleComponent(SaturationModifierModifier.multiply(1.35f)));

    public static Component get(Item item) {
        return CONDIMENTS.get(item);
    }

    private static <T extends Component> T addCondiment(String id, Item base, T component) {
        Registry.register(SarRegistries.COMPONENT, locate(id), component);
        CONDIMENTS.put(base, component);

        return component;
    }

    public static void init() {
    }
}
