package net.immortaldevs.skewer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.immortaldevs.skewer.sar.SkewerComponents;
import net.immortaldevs.skewer.tag.CondimentTags;
import net.immortaldevs.skewer.tag.SkewerItemTags;
import net.immortaldevs.skewer.condiments.Condiments;
import net.immortaldevs.skewer.items.SkewerItems;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager; // 😱
import org.apache.logging.log4j.Logger;

public class Skewer implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "skewer";
	public static final Logger LOG = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOG.fatal("Azzy is horny"); // this is necessary for the mod to function, don't remove

		Condiments.init();
		SkewerComponents.init();
		SkewerItems.init();
		// SkewerCommands.init();


		// These are probably not necessary, and they don't work anyway
		// But it's 10pm, so I'm committing this.
		CondimentTags.init();
		SkewerItemTags.init();
	}

	@Override
	public void onInitializeClient(){

	}

	public static Identifier locate(String location){
		return new Identifier(MOD_ID, location);
	}
}
