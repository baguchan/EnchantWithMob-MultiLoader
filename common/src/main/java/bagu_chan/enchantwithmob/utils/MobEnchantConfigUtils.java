package bagu_chan.enchantwithmob.utils;

import bagu_chan.enchantwithmob.EnchantConfig;
import bagu_chan.enchantwithmob.mobenchant.MobEnchant;
import bagu_chan.enchantwithmob.registry.EWRegistries;

public class MobEnchantConfigUtils {

    public static boolean isPlayerEnchantable(MobEnchant mobEnchant) {
        return !EnchantConfig.COMMON.blacklistPlayerEnchant.get().contains(EWRegistries.MOB_ENCHANT_TYPE.getKey(mobEnchant).toString());
    }
}
