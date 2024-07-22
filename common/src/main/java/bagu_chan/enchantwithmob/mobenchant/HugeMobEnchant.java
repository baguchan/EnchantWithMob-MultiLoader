package bagu_chan.enchantwithmob.mobenchant;


import bagu_chan.enchantwithmob.EnchantConfig;
import net.minecraft.world.entity.LivingEntity;

public class HugeMobEnchant extends MobEnchant {
    public HugeMobEnchant(Properties properties) {
        super(properties);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 20 + (enchantmentLevel - 1) * 10;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 20;
    }


    @Override
    public boolean isCompatibleMob(LivingEntity livingEntity) {
        return super.isCompatibleMob(livingEntity) || EnchantConfig.COMMON.bigYourSelf.get();
    }


    @Override
    public boolean isTresureEnchant() {
        return true;
    }

}
