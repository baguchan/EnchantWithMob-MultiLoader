package com.infamousmisadventures.infamousexample.registry;

import com.infamousmisadventures.infamousexample.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

import static com.infamousmisadventures.infamousexample.util.ResourceLocationHelper.modLoc;

public class ExampleCreativeTabs {
    /*public static final Supplier<CreativeModeTab> TAB = registerCreativeTab("infamous_example",
            IACreativeTabs::buildTab);

    private static @NotNull CreativeModeTab buildTab() {
        CreativeModeTab.Builder builder = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup.infamous_example")).icon(() -> new ItemStack(IAItems.BOOTS_OF_SWIFTNESS.get()));
        builder.displayItems((p_270033_, p_270034_) -> {
        });
        return builder.build();
    }*/

    public static void register() {
    }

    private static Supplier<CreativeModeTab> registerCreativeTab(String id, Supplier<CreativeModeTab> supplier) {
        return Services.REGISTRAR.registerObject(modLoc(id), supplier, BuiltInRegistries.CREATIVE_MODE_TAB);
    }
}
