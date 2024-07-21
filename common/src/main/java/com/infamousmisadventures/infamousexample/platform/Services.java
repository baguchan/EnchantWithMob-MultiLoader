package com.infamousmisadventures.infamousexample.platform;

import com.infamousmisadventures.infamousexample.ExampleConstants;
import com.infamousmisadventures.infamousexample.platform.services.INetworkHandler;
import com.infamousmisadventures.infamousexample.platform.services.IPlatformHelper;
import com.infamousmisadventures.infamousexample.platform.services.IRegistrar;
import com.infamousmisadventures.infamousexample.platform.services.IRegistryCreator;

import java.util.ServiceLoader;

public class Services {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IRegistrar REGISTRAR = load(IRegistrar.class);
    public static final IRegistryCreator REGISTRY_CREATOR = load(IRegistryCreator.class);
    public static final INetworkHandler NETWORK_HANDLER = load(INetworkHandler.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        ExampleConstants.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);

        return loadedService;
    }
}