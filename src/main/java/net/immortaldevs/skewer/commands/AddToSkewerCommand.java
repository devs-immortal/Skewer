package net.immortaldevs.skewer.commands;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.immortaldevs.skewer.condiments.Condiment;
import net.immortaldevs.skewer.items.MultiFoodItem;
import net.immortaldevs.skewer.registry.SkewerRegistries;
import net.immortaldevs.skewer.tag.CondimentTags;
import net.immortaldevs.skewer.tag.SkewerItemTags;
import net.immortaldevs.skewer.Skewer;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

// TODO rewrite or remove
public class AddToSkewerCommand {

    public static final SkewerableSuggester SKEWERABLE_SUGGESTER = new SkewerableSuggester();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        // only for dev.
        dispatcher.register(
                literal(Skewer.MOD_ID+":add")
                        .then(argument("addition", IdentifierArgumentType.identifier()).suggests(SKEWERABLE_SUGGESTER)
                                .executes(ctx -> addToSkewer(ctx.getSource(), IdentifierArgumentType.getIdentifier(ctx, "addition"), 1))
                                .then(argument("amount", IntegerArgumentType.integer(1))
                                        .executes(ctx -> addToSkewer(ctx.getSource(), IdentifierArgumentType.getIdentifier(ctx, "addition"), IntegerArgumentType.getInteger(ctx, "amount")))
                                )
                        )
        );
    }

    // todo rewrite
    private static int addToSkewer(ServerCommandSource source, Identifier id, int amount) {
        PlayerEntity player;
        try {
            player = source.getPlayer();
        } catch (CommandSyntaxException e) {
            source.sendError(new TranslatableText("commands.skewer.add.player_failure"));
            return 1;
        }

        ItemStack stack = player.getMainHandStack();
        if (!(stack.getItem() instanceof MultiFoodItem)) {
            source.sendError(new TranslatableText("commands.skewer.add.item_failure", id));
            return 1;
        }

        Item food = Registry.ITEM.get(id);
        Condiment condiment = SkewerRegistries.CONDIMENT.get(id);

//        if (!MultiFoodItem.add(stack, food, amount) && !MultiFoodItem.add(stack, condiment, amount)) {
//            source.sendError(new TranslatableText("commands.skewer.add.addition_failure", id));
//        }
        // Success message
        return 1;
    }

    public static class SkewerableSuggester implements SuggestionProvider<ServerCommandSource> {
        @Override
        public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) {
            // I think condiment tags either don't work, or are being funky for some reason.
            // Anyway, who knows if any of this helps:
            CondimentTags.SKEWERABLE.values().forEach(condiment -> {
                Identifier id = SkewerRegistries.CONDIMENT.getId(condiment);
                if (id == null)
                    System.out.println(condiment);
                else
                    builder.suggest(id.toString());
            });
            SkewerItemTags.SKEWERABLE.values().forEach(item -> builder.suggest(item.toString()));
            return builder.buildFuture();
        }
    }
}
