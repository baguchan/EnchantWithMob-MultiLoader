package com.infamousmisadventures.infamousexample.platform.services;

import com.infamousmisadventures.infamousexample.registry.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class FabricRegistrar implements IRegistrar {

    @Override
    public void setupRegistrar() {
        ExampleAttributes.register();
        ExampleItems.register();
        ExampleBlocks.register();
        ExampleCreativeTabs.register();
    }

    @Override
    public <V, T extends V> Supplier<T> registerObject(ResourceLocation objId, Supplier<T> objSup, Registry<V> targetRegistry) {
        T targetObject = Registry.register(targetRegistry, objId, objSup.get());
        return () -> targetObject;
    }
}
