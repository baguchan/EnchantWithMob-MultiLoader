package bagu_chan.enchantwithmob.capability;

import bagu_chan.enchantwithmob.mobenchant.MobEnchant;
import bagu_chan.enchantwithmob.registry.EWRegistries;
import bagu_chan.enchantwithmob.utils.MobEnchantUtils;
import net.minecraft.nbt.CompoundTag;

public class MobEnchantHandler {
    private MobEnchant mobEnchant;
    private int enchantLevel;

    public MobEnchantHandler(MobEnchant mobEnchant, int enchantLevel) {
        this.mobEnchant = mobEnchant;
        this.enchantLevel = enchantLevel;
    }


    public MobEnchant getMobEnchant() {
        return mobEnchant;
    }

    public int getEnchantLevel() {
        return enchantLevel;
    }

    public CompoundTag writeNBT() {
        CompoundTag nbt = new CompoundTag();

        if (mobEnchant != null) {
            nbt.putString("MobEnchant", EWRegistries.MOB_ENCHANT_TYPE.getKey(mobEnchant).toString());
            nbt.putInt("EnchantLevel", enchantLevel);
        }

        return nbt;
    }

    public void readNBT(CompoundTag nbt) {
        mobEnchant = MobEnchantUtils.getEnchantFromNBT(nbt);
        enchantLevel = MobEnchantUtils.getEnchantLevelFromNBT(nbt);
    }
}
