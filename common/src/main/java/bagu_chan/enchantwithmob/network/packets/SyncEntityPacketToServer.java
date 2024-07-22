package bagu_chan.enchantwithmob.network.packets;

import bagu_chan.enchantwithmob.api.IEnchantPacket;
import commonnetwork.networking.data.PacketContext;
import commonnetwork.networking.data.Side;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class SyncEntityPacketToServer {
    private UUID uuid;

    public SyncEntityPacketToServer(UUID uuid) {
        this.uuid = uuid;
    }

    public SyncEntityPacketToServer(FriendlyByteBuf buf) {
        this.uuid = buf.readUUID();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeUUID(uuid);
    }

    public static void handle(PacketContext<AncientPacket> ctx) {
        if (Side.SERVER.equals(ctx.side())) {
            Entity entity = ctx.sender();
            if (entity != null) {
                if (entity instanceof IEnchantPacket packet) {
                    packet.resync(entity);
                }
            }
        }
    }
}
