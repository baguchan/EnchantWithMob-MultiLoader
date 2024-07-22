package bagu_chan.enchantwithmob.utils;

import bagu_chan.enchantwithmob.mobenchant.MobEnchant;
import net.minecraft.util.random.WeightedEntry;

public class MobEnchantmentData extends WeightedEntry.IntrusiveBase {
    public final MobEnchant enchantment;
    public final int enchantmentLevel;

    public MobEnchantmentData(MobEnchant enchantmentObj, int enchLevel) {
        super(enchantmentObj.getRarity().getWeight());
        this.enchantment = enchantmentObj;
        this.enchantmentLevel = enchLevel;
    }
}