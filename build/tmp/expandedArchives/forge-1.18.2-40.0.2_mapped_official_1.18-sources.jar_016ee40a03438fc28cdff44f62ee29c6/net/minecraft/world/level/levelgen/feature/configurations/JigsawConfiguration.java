package net.minecraft.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class JigsawConfiguration implements FeatureConfiguration {
   public static final Codec<JigsawConfiguration> CODEC = RecordCodecBuilder.create((p_67764_) -> {
      return p_67764_.group(StructureTemplatePool.f_210555_.fieldOf("start_pool").forGetter(JigsawConfiguration::m_204802_), Codec.intRange(0, 7).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)).apply(p_67764_, JigsawConfiguration::new);
   });
   private final Holder<StructureTemplatePool> startPool;
   private final int maxDepth;

   public JigsawConfiguration(Holder<StructureTemplatePool> p_204800_, int p_204801_) {
      this.startPool = p_204800_;
      this.maxDepth = p_204801_;
   }

   public int maxDepth() {
      return this.maxDepth;
   }

   public Holder<StructureTemplatePool> m_204802_() {
      return this.startPool;
   }
}