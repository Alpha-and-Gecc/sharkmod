package net.minecraft.world.level.levelgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

public record StructureSet(List<StructureSet.StructureSelectionEntry> f_210003_, StructurePlacement f_210004_) {
   public static final Codec<StructureSet> f_210001_ = RecordCodecBuilder.create((p_210014_) -> {
      return p_210014_.group(StructureSet.StructureSelectionEntry.f_210025_.listOf().fieldOf("structures").forGetter(StructureSet::f_210003_), StructurePlacement.f_205036_.fieldOf("placement").forGetter(StructureSet::f_210004_)).apply(p_210014_, StructureSet::new);
   });
   public static final Codec<Holder<StructureSet>> f_210002_ = RegistryFileCodec.create(Registry.f_211073_, f_210001_);

   public StructureSet(Holder<ConfiguredStructureFeature<?, ?>> p_210007_, StructurePlacement p_210008_) {
      this(List.of(new StructureSet.StructureSelectionEntry(p_210007_, 1)), p_210008_);
   }

   public static StructureSet.StructureSelectionEntry m_210017_(Holder<ConfiguredStructureFeature<?, ?>> p_210018_, int p_210019_) {
      return new StructureSet.StructureSelectionEntry(p_210018_, p_210019_);
   }

   public static StructureSet.StructureSelectionEntry m_210015_(Holder<ConfiguredStructureFeature<?, ?>> p_210016_) {
      return new StructureSet.StructureSelectionEntry(p_210016_, 1);
   }

   public static record StructureSelectionEntry(Holder<ConfiguredStructureFeature<?, ?>> f_210026_, int f_210027_) {
      public static final Codec<StructureSet.StructureSelectionEntry> f_210025_ = RecordCodecBuilder.create((p_210034_) -> {
         return p_210034_.group(ConfiguredStructureFeature.CODEC.fieldOf("structure").forGetter(StructureSet.StructureSelectionEntry::f_210026_), ExtraCodecs.POSITIVE_INT.fieldOf("weight").forGetter(StructureSet.StructureSelectionEntry::f_210027_)).apply(p_210034_, StructureSet.StructureSelectionEntry::new);
      });

      public boolean m_210035_(Predicate<Holder<Biome>> p_210036_) {
         HolderSet<Biome> holderset = this.f_210026_().m_203334_().m_209752_();
         return holderset.m_203614_().anyMatch(p_210036_);
      }
   }
}