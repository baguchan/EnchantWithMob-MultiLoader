package bagu_chan.enchantwithmob;

import bagu_chan.enchantwithmob.api.IEnchantCap;
import bagu_chan.enchantwithmob.api.IEnchantedProjectile;
import bagu_chan.enchantwithmob.command.MobEnchantArgument;
import bagu_chan.enchantwithmob.command.MobEnchantingCommand;
import bagu_chan.enchantwithmob.network.packets.MobEnchantPacket;
import bagu_chan.enchantwithmob.platform.Services;
import bagu_chan.enchantwithmob.registry.EWItems;
import bagu_chan.enchantwithmob.utils.MobEnchantUtils;
import commonnetwork.api.Network;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.mixin.command.ArgumentTypesAccessor;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.config.ModConfig;

import java.util.UUID;

public class EnchantWithMobFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        EnchantWithMob.init();
        ForgeConfigRegistry.INSTANCE.register(EwConstants.MOD_ID, ModConfig.Type.COMMON, EnchantConfig.COMMON_SPEC);
        //BuiltInEnumFactories.createRaiderType("enchanter", EWEntityTypes.ENCHANTER.get(), new int[]{0, 0, 1, 0, 1, 1, 2, 1});

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.getItem() == EWItems.MOB_ENCHANT_BOOK.get() && MobEnchantUtils.hasMobEnchant(stack)) {
                if (entity instanceof IEnchantCap cap && entity instanceof LivingEntity livingEntity) {
                    MobEnchantUtils.addItemMobEnchantToEntity(stack, livingEntity, livingEntity, cap);

                    player.playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 1.0F, 1.0F);

                    stack.hurtAndBreak(1, player, (entity2) -> entity2.broadcastBreakEvent(hand));

                    return InteractionResult.SUCCESS;
                }
            }

            if (stack.getItem() == EWItems.MOB_UNENCHANT_BOOK.get() && !player.getCooldowns().isOnCooldown(stack.getItem())) {
                if (entity instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) entity;

                    if (target instanceof IEnchantCap cap) {
                        MobEnchantUtils.removeMobEnchantToEntity(target, cap);
                        player.playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 1.0F, 1.0F);

                        stack.hurtAndBreak(1, player, (entity2) -> entity2.broadcastBreakEvent(hand));

                        player.getCooldowns().addCooldown(stack.getItem(), 80);

                        return InteractionResult.SUCCESS;
                    }
                }
            }
            return InteractionResult.PASS;
        });

        /*ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            LivingEntity livingEntity = entity;

            if (source.getEntity() instanceof LivingEntity) {
                LivingEntity attacker = (LivingEntity) source.getEntity();

                if (attacker instanceof IEnchantCap cap) {
                    if (cap.getEnchantCap().hasEnchant()) {
                        int mobEnchantLevel = MobEnchantUtils.getMobEnchantLevelFromHandler(cap.getEnchantCap().getMobEnchants(), EWMobEnchants.STRONG);
                        int mobEnchantSize = cap.getEnchantCap().getMobEnchants().size();

                        //make snowman stronger
                        if (!livingEntity.isDamageSourceBlocked(source) && amount == 0) {
                            amount = MobEnchantCombatRules.getDamageAddition(1, mobEnchantLevel, mobEnchantSize);
                        } else if (amount > 0) {
                            amount = MobEnchantCombatRules.getDamageAddition(amount, mobEnchantLevel, mobEnchantSize);
                        }
                    }

                    if (cap.getEnchantCap().hasEnchant() && MobEnchantUtils.findMobEnchantFromHandler(cap.getEnchantCap().getMobEnchants(), EWMobEnchants.POISON)) {
                        int i = MobEnchantUtils.getMobEnchantLevelFromHandler(cap.getEnchantCap().getMobEnchants(), EWMobEnchants.POISON);

                        if (amount > 0) {
                            if (attacker.getRandom().nextFloat() < i * 0.125F) {
                                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60 * i, 0), attacker);
                            }
                        }
                    }


                }
            }

            if (livingEntity instanceof IEnchantCap cap) {
                if (!source.is(DamageTypeTags.BYPASSES_EFFECTS) && cap.getEnchantCap().hasEnchant()) {
                    int mobEnchantLevel = MobEnchantUtils.getMobEnchantLevelFromHandler(cap.getEnchantCap().getMobEnchants(), EWMobEnchants.PROTECTION);
                    int mobEnchantSize = cap.getEnchantCap().getMobEnchants().size();

                    amount = MobEnchantCombatRules.getDamageReduction(amount, mobEnchantLevel, mobEnchantSize);
                }
                if (source.getDirectEntity() != null) {
                    if (cap.getEnchantCap().hasEnchant()) {
                        int i = MobEnchantUtils.getMobEnchantLevelFromHandler(cap.getEnchantCap().getMobEnchants(), EWMobEnchants.THORN);

                        if (source.getDirectEntity() instanceof LivingEntity && !source.is(DamageTypeTags.IS_PROJECTILE) && !source.is(DamageTypes.THORNS) && livingEntity.getRandom().nextFloat() < i * 0.1F) {
                            LivingEntity attacker = (LivingEntity) source.getDirectEntity();

                            attacker.hurt(livingEntity.damageSources().thorns(livingEntity), MobEnchantCombatRules.getThornDamage(amount, cap.getEnchantCap()));
                        }
                    }
                }
            }
            return amount;
        });*/
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            MobEnchantingCommand.register(dispatcher);
        });
        ArgumentTypesAccessor.fabric_getClassMap().put(
                MobEnchantArgument.class, SingletonArgumentInfo.contextFree(MobEnchantArgument::mobEnchantment));

        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            if (!oldPlayer.level().isClientSide()) {
                if (oldPlayer instanceof IEnchantCap cap) {
                    for (int i = 0; i < cap.getEnchantCap().getMobEnchants().size(); i++) {
                        Network.getNetworkHandler().sendToClientsInLevel(new MobEnchantPacket(newPlayer.getId(), cap.getEnchantCap().getMobEnchants().get(i)), newPlayer.serverLevel());

                    }
                }
            }
        });
        /*LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            {
                if (id.toString().equals("minecraft:archaeology/desert_pyramid")) {
                    tableBuilder = FabricLootTableBuilder.copyOf(tableBuilder.build()).apply(LootItem.lootTableItem(EWItems.MOB_ENCHANT_BOOK.get()).apply(MobEnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 20))).build()));
                }
                if (id.toString().equals("minecraft:chests/ancient_city")) {
                    tableBuilder = FabricLootTableBuilder.copyOf(tableBuilder.build()).pool(LootPool.lootPool().add(LootItem.lootTableItem(EWItems.MOB_ENCHANT_BOOK.get()).apply(MobEnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(30, 40)))).build());
                }
                if (id.toString().equals("minecraft:chests/wood_land_mansion")) {
                    tableBuilder = FabricLootTableBuilder.copyOf(tableBuilder.build()).pool(LootPool.lootPool().add(LootItem.lootTableItem(EWItems.MOB_ENCHANT_BOOK.get()).apply(MobEnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20, 30)))).add(LootItem.lootTableItem(EWItems.ENCHANTERS_BOOK.get()).apply(MobEnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20, 30)))).build());
                }
                if (id.toString().equals("minecraft:chests/stronghold_library")) {
                    tableBuilder = FabricLootTableBuilder.copyOf(tableBuilder.build()).pool(LootPool.lootPool().add(LootItem.lootTableItem(EWItems.MOB_ENCHANT_BOOK.get()).apply(MobEnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20, 30)))).build());
                }
            }
        });*/
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, serverLevel) -> {
            if (player instanceof IEnchantCap enchantCap) {
                if (!serverLevel.isClientSide) {
                    for (int i = 0; i < enchantCap.getEnchantCap().getMobEnchants().size(); i++) {
                        MobEnchantPacket message = new MobEnchantPacket(player.getId(), enchantCap.getEnchantCap().getMobEnchants().get(i));
                        Services.NETWORK_HANDLER.sendToClient(message, player);
                    }
                }
            }
        });

    }

    private static void addProjectile(Projectile projectile, CompoundTag compoundNBT, Level level, float rotation) {
        Projectile newProjectile = (Projectile) projectile.getType().create(level);
        UUID uuid = newProjectile.getUUID();
        newProjectile.load(compoundNBT);
        newProjectile.setUUID(uuid);
        Vec3 vector3d = newProjectile.getDeltaMovement().yRot((float) (Math.PI / rotation));

        newProjectile.setDeltaMovement(vector3d);
        float f = Mth.sqrt((float) vector3d.horizontalDistanceSqr());
        newProjectile.setYRot((float) (Mth.atan2(vector3d.x, vector3d.z) * (double) (180F / (float) Math.PI)));
        newProjectile.setXRot((float) (Mth.atan2(vector3d.y, (double) f) * (double) (180F / (float) Math.PI)));
        newProjectile.yRotO = newProjectile.getYRot();
        newProjectile.xRotO = newProjectile.getXRot();
        if (newProjectile instanceof Projectile) {
            Projectile newDamagingProjectile = (Projectile) newProjectile;
            Vec3 newPower = new Vec3(newDamagingProjectile.getDeltaMovement().x, newDamagingProjectile.getDeltaMovement().y, newDamagingProjectile.getDeltaMovement().z).yRot((float) (Math.PI / rotation));

            newDamagingProjectile.setDeltaMovement(newPower);
        }

        if (newProjectile instanceof AbstractArrow) {
            ((AbstractArrow) newProjectile).pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }

        if (newProjectile instanceof IEnchantedProjectile enchantedProjectile) {
            enchantedProjectile.setEnchantProjectile(true);
        }

        level.addFreshEntity(newProjectile);
    }
}
