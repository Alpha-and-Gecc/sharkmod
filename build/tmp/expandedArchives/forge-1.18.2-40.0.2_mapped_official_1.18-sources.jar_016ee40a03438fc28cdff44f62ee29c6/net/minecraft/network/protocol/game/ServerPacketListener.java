package net.minecraft.network.protocol.game;

import net.minecraft.network.PacketListener;

public interface ServerPacketListener extends PacketListener {
   default boolean m_201767_() {
      return false;
   }
}