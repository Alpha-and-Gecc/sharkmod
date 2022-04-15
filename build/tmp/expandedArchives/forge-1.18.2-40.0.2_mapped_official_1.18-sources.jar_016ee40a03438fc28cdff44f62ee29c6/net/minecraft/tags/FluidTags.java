package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;

public final class FluidTags {
   public static final TagKey<Fluid> WATER = m_203850_("water");
   public static final TagKey<Fluid> LAVA = m_203850_("lava");

   private FluidTags() {
   }

   private static TagKey<Fluid> m_203850_(String p_203851_) {
      return TagKey.m_203882_(Registry.FLUID_REGISTRY, new ResourceLocation(p_203851_));
   }

   public static TagKey<Fluid> create(ResourceLocation name) {
      return TagKey.m_203882_(Registry.FLUID_REGISTRY, name);
   }
}
