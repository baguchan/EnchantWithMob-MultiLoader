package bagu_chan.enchantwithmob.network.packets;

import bagu_chan.enchantwithmob.api.IEnchantCap;
import bagu_chan.enchantwithmob.capability.MobEnchantCapability;
import commonnetwork.networking.data.PacketContext;
import commonnetwork.networking.data.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import static bagu_chan.enchantwithmob.utils.ResourceLocationHelper.modLoc;

public class AncientPacket {
    public static final ResourceLocation CHANNEL = modLoc("ancient");

    private int entityID;
    private boolean ancient;

    public AncientPacket(LivingEntity entity, boolean ancient) {
        this.entityID = entity.getId();
        this.ancient = ancient;
    }

    public AncientPacket(int entityId, boolean ancient) {
        this.entityID = entityId;
        this.ancient = ancient;
    }

    public AncientPacket(FriendlyByteBuf buf) {
        this.entityID = buf.readInt();
        this.ancient = buf.readBoolean();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(entityID);
        buffer.writeBoolean(ancient);
    }

    public static void handle(PacketContext<AncientPacket> ctx) {
        if (Side.CLIENT.equals(ctx.side())) {
            Entity entity = Minecraft.getInstance().player.level().getEntity(ctx.message().entityID);
            if (entity != null && entity instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof IEnchantCap cap) {
                    cap.getEnchantCap().setEnchantType(livingEntity, ctx.message().ancient ? MobEnchantCapability.EnchantType.ANCIENT : MobEnchantCapability.EnchantType.NORMAL);
                }
            }
        }
    }
}
