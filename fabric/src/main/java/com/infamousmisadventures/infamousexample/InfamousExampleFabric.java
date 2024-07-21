package com.infamousmisadventures.infamousexample;

import net.fabricmc.api.ModInitializer;

public class InfamousExampleFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        InfamousExample.init();
        setupDatapackFormats();
    }

    private void setupDatapackFormats() {
        //CodecDataManagerSync.subscribeAsSyncable(ArtifactGearConfigSyncPacket::new, EXAMPLE_DATAPACK);
    }
}
