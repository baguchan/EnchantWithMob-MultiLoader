package com.infamousmisadventures.infamousexample.network;

import com.infamousmisadventures.infamousexample.network.packets.ExamplePacket;
import commonnetwork.api.Network;

public class RegisterPackets {

    public static void registerPackets(){
        Network
                .registerPacket(ExamplePacket.CHANNEL, ExamplePacket.class, ExamplePacket::encode, ExamplePacket::new, ExamplePacket::handle)
        ;
    }
}
