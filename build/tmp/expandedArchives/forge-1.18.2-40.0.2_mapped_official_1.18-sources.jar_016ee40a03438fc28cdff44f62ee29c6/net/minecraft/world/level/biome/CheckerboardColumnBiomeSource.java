package net.minecraft.world.level.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;

public class CheckerboardColumnBiomeSource extends BiomeSource {
   public static final Codec<CheckerboardColumnBiomeSource> CODEC = RecordCodecBuilder.create((p_48244_) -> {
      return p_48244_.group(Biome.LIST_CODEC.fieldOf("biomes").forGetter((p_204246_) -> {
         return p_204246_.allowedBiomes;
      }), Codec.intRange(0, 62).fieldOf("scale").orElse(2).forGetter((p_151788_) -> {
         return p_151788_.size;
      })).apply(p_48244_, CheckerboardColumnBiomeSource::new);
   });
   private final HolderSet<Biome> allowedBiomes;
   private final int bitShift;
   private final int size;

   public CheckerboardColumnBiomeSource(HolderSet<Biome> p_204243_, int p_204244_) {
      super(p_204243_.m_203614_());
      this.allowedBiomes = p_204243_;
      this.bitShift = p_204244_ + 2;
      this.size = p_204244_;
   }

   protected Codec<? extends BiomeSource> codec() {
      return CODEC;
   }

   public BiomeSource withSeed(long p_48240_) {
      return this;
   }

   public Holder<Biome> m_203407_(int p_204248_, int p_204249_, int p_204250_, Climate.Sampler p_204251_) {
      return this.allowedBiomes.m_203662_(Math.floorMod((p_204248_ >> this.bitShift) + (p_204250_ >> this.bitShift), this.allowedBiomes.m_203632_()));
   }
}