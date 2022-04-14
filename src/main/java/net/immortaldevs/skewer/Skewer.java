package net.immortaldevs.skewer;

import net.fabricmc.api.ModInitializer;
import net.immortaldevs.skewer.component.SkewerComponents;
import net.immortaldevs.skewer.component.condiment.Condiments;
import net.immortaldevs.skewer.component.food.SkewerableFoods;
import net.immortaldevs.skewer.item.SkewerItems;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Skewer implements ModInitializer {
	public static final String SKEWER = "skewer";
	public static final Logger LOGGER = LogManager.getLogger(SKEWER);

	@Override
	public void onInitialize() {
		LOGGER.fatal("Azzy is horny"); // this is necessary for the mod to function, don't remove

		SkewerItems.init();
		SkewerComponents.init();
		SkewerableFoods.init();
		Condiments.init();
	}

	public static Identifier id(String path) {
		return new Identifier(SKEWER, path);
	}
}