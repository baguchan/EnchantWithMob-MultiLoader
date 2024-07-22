package bagu_chan.enchantwithmob;

import bagu_chan.enchantwithmob.command.MobEnchantingCommand;
import bagu_chan.enchantwithmob.registry.EwArgumentTypeInfos;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EwConstants.MOD_ID)
public class EnchantWithMobForge {

    public EnchantWithMobForge() {
        EnchantWithMob.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerCommands);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EnchantConfig.COMMON_SPEC);
        EwArgumentTypeInfos.ARGUMENT_TYPE_CLASSES.forEach(ArgumentTypeInfos::registerByClass);
    }

    private void registerCommands(RegisterCommandsEvent evt) {
        MobEnchantingCommand.register(evt.getDispatcher());
    }


    private void setupClient(final FMLClientSetupEvent event) {
        event.enqueueWork(EnchantWithMobForgeClient::setupClient);
    }
}