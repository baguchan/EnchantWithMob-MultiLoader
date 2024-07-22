package bagu_chan.enchantwithmob.network.packets;

import bagu_chan.enchantwithmob.api.IEnchantCap;
import commonnetwork.networking.data.PacketContext;
import commonnetwork.networking.data.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import static bagu_chan.enchantwithmob.utils.ResourceLocationHelper.modLoc;

public class MobEnchantFromOwnerPacket {
    public static final ResourceLocation CHANNEL = modLoc("mob_enchant_from_owner");
    private int entityId;
    private int ownerId;


    public MobEnchantFromOwnerPacket(int entityId, int ownerId) {
        this.entityId = entityId;
        this.ownerId = ownerId;
    }

    public MobEnchantFromOwnerPacket(FriendlyByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.ownerId = buffer.readInt();
    }


    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
        buffer.writeInt(ownerId);
    }

    public static void handle(PacketContext<MobEnchantFromOwnerPacket> ctx) {
        if (Side.CLIENT.equals(ctx.side())) {
            Entity entity = Minecraft.getInstance().player.level().getEntity(ctx.message().entityId);
            Entity owner = Minecraft.getInstance().player.level().getEntity(ctx.message().ownerId);
            if (entity != null && owner instanceof LivingEntity livingOnwer && entity instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof IEnchantCap cap) {
                    cap.getEnchantCap().addOwner((LivingEntity) entity, livingOnwer);
                }
            }
        }
    }
}