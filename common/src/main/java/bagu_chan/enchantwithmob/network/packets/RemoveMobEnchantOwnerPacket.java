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

public class RemoveMobEnchantOwnerPacket {
    public static final ResourceLocation CHANNEL = modLoc("remove_mob_enchant_owner");
    private int entityId;


    public RemoveMobEnchantOwnerPacket(int entityId) {
        this.entityId = entityId;
    }

    public RemoveMobEnchantOwnerPacket(FriendlyByteBuf buffer) {
        this.entityId = buffer.readInt();
    }


    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
    }

    public static void handle(PacketContext<RemoveMobEnchantOwnerPacket> ctx) {
        if (Side.CLIENT.equals(ctx.side())) {
            Entity entity = Minecraft.getInstance().player.level().getEntity(ctx.message().entityId);
            if (entity != null && entity instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof IEnchantCap cap) {
                    cap.getEnchantCap().removeOwner((LivingEntity) entity);
                }
            }
        }
    }
}