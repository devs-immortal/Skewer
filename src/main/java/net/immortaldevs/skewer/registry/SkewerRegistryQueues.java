package net.immortaldevs.skewer.registry;

import net.id.incubus_core.util.RegistryQueue;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.skewer.Skewer.MOD_ID;

public class SkewerRegistryQueues {
    public static RegistryQueue<Item> ITEMS = new RegistryQueue<>(Registry.ITEM, MOD_ID, 5);
}
