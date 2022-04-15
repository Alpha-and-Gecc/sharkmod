package net.minecraft.world.level.levelgen.structure.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;

public interface StructurePlacementType<SP extends StructurePlacement> {
   StructurePlacementType<RandomSpreadStructurePlacement> f_205041_ = m_205046_("random_spread", RandomSpreadStructurePlacement.f_204972_);
   StructurePlacementType<ConcentricRingsStructurePlacement> f_205042_ = m_205046_("concentric_rings", ConcentricRingsStructurePlacement.f_204949_);

   Codec<SP> m_205049_();

   private static <SP extends StructurePlacement> StructurePlacementType<SP> m_205046_(String p_205047_, Codec<SP> p_205048_) {
      return Registry.register(Registry.f_205930_, p_205047_, () -> {
         return p_205048_;
      });
   }
}