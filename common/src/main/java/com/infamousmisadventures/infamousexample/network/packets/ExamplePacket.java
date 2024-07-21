package com.infamousmisadventures.infamousexample.network.packets;

import com.infamousmisadventures.infamousexample.ExampleConstants;
import commonnetwork.networking.data.PacketContext;
import commonnetwork.networking.data.Side;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import static com.infamousmisadventures.infamousexample.util.ResourceLocationHelper.modLoc;

public class ExamplePacket {
    public static final ResourceLocation CHANNEL = modLoc("example");
    private final int exampleInt;

    public ExamplePacket(int exampleInt) {
        this.exampleInt = exampleInt;
    }

    public ExamplePacket(FriendlyByteBuf buffer) {
        exampleInt = buffer.readInt();
    }


    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(exampleInt);
    }

    public static void handle(PacketContext<ExamplePacket> ctx) {
        if (Side.CLIENT.equals(ctx.side()))
        {
            ExampleConstants.LOGGER.error("ExamplePacket received on the client");
        }
        else
        {
            ExampleConstants.LOGGER.error("ExamplePacket received on the server");
        }
    }
}