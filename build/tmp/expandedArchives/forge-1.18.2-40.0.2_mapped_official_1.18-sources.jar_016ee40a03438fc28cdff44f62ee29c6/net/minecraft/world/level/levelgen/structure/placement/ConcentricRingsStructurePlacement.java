package net.minecraft.world.level.levelgen.structure.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;

public record ConcentricRingsStructurePlacement(int f_204950_, int f_204951_, int f_204952_) implements StructurePlacement {
   public static final Codec<ConcentricRingsStructurePlacement> f_204949_ = RecordCodecBuilder.create((p_204960_) -> {
      return p_204960_.group(Codec.intRange(0, 1023).fieldOf("distance").forGetter(ConcentricRingsStructurePlacement::f_204950_), Codec.intRange(0, 1023).fieldOf("spread").forGetter(ConcentricRingsStructurePlacement::f_204951_), Codec.intRange(1, 4095).fieldOf("count").forGetter(ConcentricRingsStructurePlacement::f_204952_)).apply(p_204960_, ConcentricRingsStructurePlacement::new);
   });

   public boolean m_212129_(ChunkGenerator p_212310_, long p_212311_, int p_212312_, int p_212313_) {
      List<ChunkPos> list = p_212310_.m_204380_(this);
      return list == null ? false : list.contains(new ChunkPos(p_212312_, p_212313_));
   }

   public StructurePlacementType<?> m_203443_() {
      return StructurePlacementType.f_205042_;
   }
}