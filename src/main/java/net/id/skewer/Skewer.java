package net.id.skewer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.id.skewer.items.SkewerItems;
import net.id.skewer.condiments.Condiments;

public class Skewer implements ModInitializer, ClientModInitializer {
	public static String MOD_ID = "skewer";

	@Override
	public void onInitialize() {
		System.out.println("Azzy is horny"); // this is necessary for the mod to function, don't remove

		SkewerItems.init();
		Condiments.init();
	}

	@Override
	public void onInitializeClient(){

	}

	public static Identifier locate(String location){
		return new Identifier(MOD_ID, location);
	}
}
