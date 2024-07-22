package bagu_chan.enchantwithmob.network.packets;

import bagu_chan.enchantwithmob.api.IEnchantCap;
import bagu_chan.enchantwithmob.capability.MobEnchantHandler;
import bagu_chan.enchantwithmob.mobenchant.MobEnchant;
import bagu_chan.enchantwithmob.registry.EWRegistries;
import bagu_chan.enchantwithmob.utils.MobEnchantUtils;
import commonnetwork.networking.data.PacketContext;
import commonnetwork.networking.data.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import static bagu_chan.enchantwithmob.utils.ResourceLocationHelper.modLoc;


public class MobEnchantPacket {
    public static final ResourceLocation CHANNEL = modLoc("example");
    private int entityId;
    private MobEnchant enchantType;
    private int level;


    public MobEnchantPacket(int entityId, MobEnchantHandler handler) {
        this.entityId = entityId;
        this.enchantType = handler.getMobEnchant();
        this.level = handler.getEnchantLevel();
    }

    public MobEnchantPacket(int entityId, MobEnchant mobEnchant, int level) {
        this.entityId = entityId;
        this.enchantType = mobEnchant;
        this.level = level;

    }

    public MobEnchantPacket(FriendlyByteBuf buffer) {
        this.entityId = buffer.readInt();
        enchantType = EWRegistries.MOB_ENCHANT_TYPE.get(ResourceLocation.tryParse(buffer.readUtf()));
        this.level = buffer.readInt();
    }


    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
        buffer.writeUtf(EWRegistries.MOB_ENCHANT_TYPE.getKey(enchantType).toString());
        buffer.writeInt(level);
    }

    public static void handle(PacketContext<MobEnchantPacket> ctx) {
        if (Side.CLIENT.equals(ctx.side()))
        {
            Entity entity = Minecraft.getInstance().player.level().getEntity(ctx.message().entityId);
            if (entity != null && entity instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof IEnchantCap cap) {
                    if (!MobEnchantUtils.findMobEnchantHandler(cap.getEnchantCap().getMobEnchants(), ctx.message().enchantType)) {
                        cap.getEnchantCap().addMobEnchant((LivingEntity) entity, ctx.message().enchantType, ctx.message().level);
                    }
                }
            }
        }
    }
}