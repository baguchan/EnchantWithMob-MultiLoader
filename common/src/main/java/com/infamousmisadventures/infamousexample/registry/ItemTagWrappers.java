package com.infamousmisadventures.infamousexample.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.infamousmisadventures.infamousexample.util.ResourceLocationHelper.modLoc;

public class ItemTagWrappers {

    private static TagKey<Item> tag(String name) {
        return TagKey.create(Registries.ITEM, modLoc(name));
    }

    private static TagKey<Item> tag(ResourceLocation resourceLocation) {
        return TagKey.create(Registries.ITEM, resourceLocation);
    }

    public static void init() {
    }
}