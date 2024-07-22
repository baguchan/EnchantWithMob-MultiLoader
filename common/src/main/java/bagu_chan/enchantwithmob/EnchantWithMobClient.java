package bagu_chan.enchantwithmob;


import bagu_chan.enchantwithmob.client.ModModelLayers;
import bagu_chan.enchantwithmob.client.model.EnchanterModel;
import bagu_chan.enchantwithmob.client.render.EnchanterRenderer;
import bagu_chan.enchantwithmob.platform.Services;
import bagu_chan.enchantwithmob.registry.EWEntityTypes;

public class EnchantWithMobClient {

    public static void client() {
        Services.RENDER_REGISTRY.entityModel(EWEntityTypes.ENCHANTER, EnchanterRenderer::new);
        Services.RENDER_REGISTRY.layerDefinition(ModModelLayers.ENCHANTER, EnchanterModel::createBodyLayer);
    }
}