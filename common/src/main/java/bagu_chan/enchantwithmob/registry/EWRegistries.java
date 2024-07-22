package bagu_chan.enchantwithmob.registry;

import bagu_chan.enchantwithmob.mobenchant.MobEnchant;
import bagu_chan.enchantwithmob.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import static bagu_chan.enchantwithmob.utils.ResourceLocationHelper.modLoc;

public class EWRegistries {
    public static Registry<MobEnchant> MOB_ENCHANT_TYPE = createRegistry(MobEnchant.class, modLoc("mob_enchant"));

    private static <P> Registry<P> createRegistry(Class<P> classType, ResourceLocation registryLocation) {
        return Services.REGISTRY_CREATOR.createRegistry(classType, registryLocation);
    }

}
