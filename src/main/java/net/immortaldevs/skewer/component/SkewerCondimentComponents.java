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

import javax.annotation.Nullable;

import static net.immortaldevs.skewer.Skewer.locate;

public class SkewerCondimentComponents {
    private static final Reference2ReferenceMap<Item, CondimentComponent> CONDIMENTS =
            new Reference2ReferenceOpenHashMap<>();

    public static final Component KELP_GOO = add("kelp_goo", SkewerItems.KELP_GOO, new CondimentComponent());
    public static final SimpleComponent AIOLI = add("aioli", SkewerItems.AIOLI, new CondimentComponent(HungerModifier.add(2), SaturationModifierModifier.multiply(1.2f)));
    // Is this spelling on purpose?

    // What spelling?
    public static final SimpleComponent CHILLI_SAUCE = add("chilli_sauce", SkewerItems.CHILLI_SAUCE, new CondimentComponent(SaturationModifierModifier.multiply(1.35f)));

    public static @Nullable CondimentComponent get(Item item) {
        return CONDIMENTS.get(item);
    }

    private static <T extends CondimentComponent> T add(String id, Item base, T component) {
        Registry.register(SarRegistries.COMPONENT, locate(id), component);
        CONDIMENTS.put(base, component);

        return component;
    }

    public static void init() {
    }
}
