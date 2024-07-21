package com.infamousmisadventures.infamousexample.registry;

import com.infamousmisadventures.infamousexample.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

import static com.infamousmisadventures.infamousexample.util.ResourceLocationHelper.modLoc;

public class ExampleItems {
    //public static final Supplier<Item> DIRT = registerArtifact("dirt_artifact", () -> new AbstractArtifact(ARTIFACT_PROPERTIES));

    public static void register() {
    }

    private static Supplier<Item> registerItem(String id, Supplier<Item> attribSup) {
        return Services.REGISTRAR.registerObject(modLoc(id), attribSup, BuiltInRegistries.ITEM);
    }
}
