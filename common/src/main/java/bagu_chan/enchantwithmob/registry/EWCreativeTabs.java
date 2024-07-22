package bagu_chan.enchantwithmob.registry;

import bagu_chan.enchantwithmob.item.MobEnchantBookItem;
import bagu_chan.enchantwithmob.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static bagu_chan.enchantwithmob.utils.ResourceLocationHelper.modLoc;


public class EWCreativeTabs {
    public static final Supplier<CreativeModeTab> TAB = registerCreativeTab("enchantwithmob",
            EWCreativeTabs::buildTab);

    private static @NotNull CreativeModeTab buildTab() {
        CreativeModeTab.Builder builder = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup.enchantwithmob")).icon(() -> new ItemStack(EWItems.MOB_ENCHANT_BOOK.get()));
        builder.displayItems((p_270033_, p_270034_) -> {
            p_270034_.acceptAll(MobEnchantBookItem.generateMobEnchantmentBookTypesOnlyMaxLevel());
            p_270034_.accept(EWItems.MOB_UNENCHANT_BOOK.get());
            p_270034_.accept(EWItems.ENCHANTER_SPAWNEGG.get());
        });
        return builder.build();
    }

    public static void register() {
    }

    private static Supplier<CreativeModeTab> registerCreativeTab(String id, Supplier<CreativeModeTab> supplier) {
        return Services.REGISTRAR.registerObject(modLoc(id), supplier, BuiltInRegistries.CREATIVE_MODE_TAB);
    }
}
