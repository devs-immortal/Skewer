package net.id.skewer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.id.skewer.commands.SkewerCommands;
import net.id.skewer.sar.SkewerComponents;
import net.id.skewer.tag.CondimentTags;
import net.id.skewer.tag.SkewerItemTags;
import net.minecraft.util.Identifier;
import net.id.skewer.items.SkewerItems;
import net.id.skewer.condiments.Condiments;
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
		SkewerCommands.init();


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
