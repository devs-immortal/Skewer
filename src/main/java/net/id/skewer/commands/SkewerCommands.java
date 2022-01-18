package net.id.skewer.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;

import java.util.function.Consumer;

public class SkewerCommands {

    public static void init() {
        // only for dev
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            register(AddToSkewerCommand::register);
        }
    }

    private static void register(Consumer<CommandDispatcher<ServerCommandSource>> command) {
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> command.accept(dispatcher)));
    }
}
