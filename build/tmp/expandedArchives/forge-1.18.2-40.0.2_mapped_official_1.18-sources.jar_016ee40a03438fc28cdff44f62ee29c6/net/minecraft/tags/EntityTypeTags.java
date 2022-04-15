package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

public final class EntityTypeTags {
   public static final TagKey<EntityType<?>> SKELETONS = m_203848_("skeletons");
   public static final TagKey<EntityType<?>> RAIDERS = m_203848_("raiders");
   public static final TagKey<EntityType<?>> BEEHIVE_INHABITORS = m_203848_("beehive_inhabitors");
   public static final TagKey<EntityType<?>> ARROWS = m_203848_("arrows");
   public static final TagKey<EntityType<?>> IMPACT_PROJECTILES = m_203848_("impact_projectiles");
   public static final TagKey<EntityType<?>> POWDER_SNOW_WALKABLE_MOBS = m_203848_("powder_snow_walkable_mobs");
   public static final TagKey<EntityType<?>> AXOLOTL_ALWAYS_HOSTILES = m_203848_("axolotl_always_hostiles");
   public static final TagKey<EntityType<?>> AXOLOTL_HUNT_TARGETS = m_203848_("axolotl_hunt_targets");
   public static final TagKey<EntityType<?>> FREEZE_IMMUNE_ENTITY_TYPES = m_203848_("freeze_immune_entity_types");
   public static final TagKey<EntityType<?>> FREEZE_HURTS_EXTRA_TYPES = m_203848_("freeze_hurts_extra_types");

   private EntityTypeTags() {
   }

   private static TagKey<EntityType<?>> m_203848_(String p_203849_) {
      return TagKey.m_203882_(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(p_203849_));
   }
}