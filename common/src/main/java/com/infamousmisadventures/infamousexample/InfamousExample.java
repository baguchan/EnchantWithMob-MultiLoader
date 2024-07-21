package com.infamousmisadventures.infamousexample;

import com.infamousmisadventures.infamousexample.platform.Services;

public class InfamousExample {

    public static void init() {
        Services.REGISTRAR.setupRegistrar();
        Services.REGISTRY_CREATOR.setupRegistryCreator();
        Services.NETWORK_HANDLER.setupNetworkHandler();
    }
}