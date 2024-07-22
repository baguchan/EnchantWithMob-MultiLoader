package bagu_chan.enchantwithmob;

import bagu_chan.enchantwithmob.platform.Services;

public class EnchantWithMob {

    public static void init() {
        Services.REGISTRAR.setupRegistrar();
        Services.REGISTRY_CREATOR.setupRegistryCreator();
        Services.NETWORK_HANDLER.setupNetworkHandler();
    }
}