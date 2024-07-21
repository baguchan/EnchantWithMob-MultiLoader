package com.infamousmisadventures.infamousexample.registry;

import com.infamousmisadventures.infamousexample.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class ExampleRegistries {
    //public static Registry<TargettedComponentType> TARGETTED_COMPONENT_TYPE = createRegistry(TargettedComponentType.class, modLoc("targetted_component_type"));

    private static <P> Registry<P> createRegistry(Class<P> classType, ResourceLocation registryLocation) {
        return Services.REGISTRY_CREATOR.createRegistry(classType, registryLocation);
    }

}
