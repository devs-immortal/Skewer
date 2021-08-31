package net.skewer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Skewer implements ModInitializer, ClientModInitializer {
	public static String MOD_ID = "skewer";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Azzy is horny");
	}

	@Override
	public void onInitializeClient(){

	}

	public static Identifier locate(String location){
		return new Identifier(MOD_ID, location);
	}
}
