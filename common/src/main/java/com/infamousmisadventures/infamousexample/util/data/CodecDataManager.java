package com.infamousmisadventures.infamousexample.util.data;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public interface CodecDataManager<T> {

    Map<ResourceLocation, T> getData();
}