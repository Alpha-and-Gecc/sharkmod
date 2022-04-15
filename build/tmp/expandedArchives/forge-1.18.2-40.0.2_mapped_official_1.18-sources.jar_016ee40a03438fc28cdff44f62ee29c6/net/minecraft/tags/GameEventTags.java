package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.gameevent.GameEvent;

public class GameEventTags {
   public static final TagKey<GameEvent> VIBRATIONS = m_203852_("vibrations");
   public static final TagKey<GameEvent> IGNORE_VIBRATIONS_SNEAKING = m_203852_("ignore_vibrations_sneaking");

   private static TagKey<GameEvent> m_203852_(String p_203853_) {
      return TagKey.m_203882_(Registry.GAME_EVENT_REGISTRY, new ResourceLocation(p_203853_));
   }
}