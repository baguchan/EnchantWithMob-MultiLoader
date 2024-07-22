package bagu_chan.enchantwithmob;

import net.fabricmc.api.ClientModInitializer;

public class EnchantWithMobClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EnchantWithMobClient.client();
    }
}
