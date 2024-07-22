package bagu_chan.enchantwithmob.command;

import bagu_chan.enchantwithmob.EnchantConfig;
import bagu_chan.enchantwithmob.mobenchant.MobEnchant;
import bagu_chan.enchantwithmob.registry.EWRegistries;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class MobEnchantArgument implements ArgumentType<MobEnchant> {
    private static final Collection<String> EXAMPLES = Arrays.asList("enchantwithmob:strong", "enchantwithmob:protection");
    public static final DynamicCommandExceptionType ERROR_UNKNOWN_EFFECT = new DynamicCommandExceptionType((p_208663_0_) -> {
        return Component.translatable("mobEnchantment.mobEnchantmentNotFound", p_208663_0_);
    });

    public static MobEnchantArgument mobEnchantment() {
        return new MobEnchantArgument();
    }

    public static MobEnchant getMobEnchant(CommandContext<CommandSourceStack> source, String name) {
        return source.getArgument(name, MobEnchant.class);
    }

    public MobEnchant parse(StringReader p_parse_1_) throws CommandSyntaxException {
        ResourceLocation resourcelocation = ResourceLocation.read(p_parse_1_);
        if (EWRegistries.MOB_ENCHANT_TYPE.containsKey(resourcelocation)) {
            return EWRegistries.MOB_ENCHANT_TYPE.get(resourcelocation);
        } else {
            throw ERROR_UNKNOWN_EFFECT.create(resourcelocation);
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return SharedSuggestionProvider.suggestResource(EWRegistries.MOB_ENCHANT_TYPE.keySet().stream().filter(resource -> !EnchantConfig.COMMON.disableEnchants.get().contains(resource.toString())), p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}