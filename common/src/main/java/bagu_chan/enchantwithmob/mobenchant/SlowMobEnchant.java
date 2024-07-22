package bagu_chan.enchantwithmob.mobenchant;

import bagu_chan.enchantwithmob.EnchantConfig;
import bagu_chan.enchantwithmob.registry.EWMobEnchants;
import net.minecraft.world.entity.LivingEntity;

public class SlowMobEnchant extends MobEnchant {
    public SlowMobEnchant(Properties properties) {
        super(properties);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + (enchantmentLevel - 1) * 10;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 20;
    }

    @Override
    public boolean isTresureEnchant() {
        return true;
    }

    public boolean isCursedEnchant() {
        return true;
    }

    @Override
    public boolean isCompatibleMob(LivingEntity livingEntity) {
        return super.isCompatibleMob(livingEntity) || EnchantConfig.COMMON.bigYourSelf.get();
    }

    @Override
    protected boolean canApplyTogether(MobEnchant ench) {
        return super.canApplyTogether(ench) && ench != EWMobEnchants.SPEEDY && ench != EWMobEnchants.HASTE;
    }
}