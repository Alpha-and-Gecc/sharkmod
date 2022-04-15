package net.minecraft.world.level.levelgen.structure.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.chunk.ChunkGenerator;

public interface StructurePlacement {
   Codec<StructurePlacement> f_205036_ = Registry.f_205930_.byNameCodec().dispatch(StructurePlacement::m_203443_, StructurePlacementType::m_205049_);

   boolean m_212129_(ChunkGenerator p_212319_, long p_212320_, int p_212321_, int p_212322_);

   StructurePlacementType<?> m_203443_();
}