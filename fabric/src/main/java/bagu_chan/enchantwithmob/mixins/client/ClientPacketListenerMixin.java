package bagu_chan.enchantwithmob.mixins.client;

import bagu_chan.enchantwithmob.api.IEnchantPacket;
import bagu_chan.enchantwithmob.network.packets.SyncEntityPacketToServer;
import bagu_chan.enchantwithmob.platform.Services;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(method = "handleAddEntity(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;getId()I"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void syncEntity(
            ClientboundAddEntityPacket $$0, CallbackInfo ci, EntityType $$1, Entity entity) {
        if (entity instanceof IEnchantPacket && entity.level().isClientSide()) {
            Services.NETWORK_HANDLER.sendToServer(new SyncEntityPacketToServer(entity.getUUID()));
        }
    }
}