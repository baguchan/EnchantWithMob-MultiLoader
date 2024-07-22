package bagu_chan.enchantwithmob.network;

import bagu_chan.enchantwithmob.network.packets.*;
import commonnetwork.api.Network;

public class RegisterPackets {

    public static void registerPackets(){
        Network
                .registerPacket(MobEnchantPacket.CHANNEL, MobEnchantPacket.class, MobEnchantPacket::encode, MobEnchantPacket::new, MobEnchantPacket::handle)
        ;
        Network
                .registerPacket(RemoveAllMobEnchantPacket.CHANNEL, RemoveAllMobEnchantPacket.class, RemoveAllMobEnchantPacket::encode, RemoveAllMobEnchantPacket::new, RemoveAllMobEnchantPacket::handle)
        ;
        Network
                .registerPacket(AncientPacket.CHANNEL, AncientPacket.class, AncientPacket::encode, AncientPacket::new, AncientPacket::handle)
        ;
        Network
                .registerPacket(RemoveMobEnchantOwnerPacket.CHANNEL, RemoveMobEnchantOwnerPacket.class, RemoveMobEnchantOwnerPacket::encode, RemoveMobEnchantOwnerPacket::new, RemoveMobEnchantOwnerPacket::handle)
        ;
        Network
                .registerPacket(MobEnchantFromOwnerPacket.CHANNEL, MobEnchantFromOwnerPacket.class, MobEnchantFromOwnerPacket::encode, MobEnchantFromOwnerPacket::new, MobEnchantFromOwnerPacket::handle)
        ;
    }
}
