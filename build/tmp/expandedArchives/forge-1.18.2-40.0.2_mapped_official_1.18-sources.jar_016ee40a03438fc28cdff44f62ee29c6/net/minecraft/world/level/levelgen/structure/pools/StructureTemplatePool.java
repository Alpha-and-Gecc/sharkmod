package net.minecraft.world.level.levelgen.structure.pools;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.GravityProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import org.slf4j.Logger;

public class StructureTemplatePool {
   private static final Logger f_210556_ = LogUtils.getLogger();
   private static final int f_210557_ = Integer.MIN_VALUE;
   public static final Codec<StructureTemplatePool> f_210554_ = RecordCodecBuilder.create((p_210575_) -> {
      return p_210575_.group(ResourceLocation.CODEC.fieldOf("name").forGetter(StructureTemplatePool::m_210587_), ResourceLocation.CODEC.fieldOf("fallback").forGetter(StructureTemplatePool::m_210573_), Codec.mapPair(StructurePoolElement.f_210468_.fieldOf("element"), Codec.intRange(1, 150).fieldOf("weight")).codec().listOf().fieldOf("elements").forGetter((p_210579_) -> {
         return p_210579_.f_210559_;
      })).apply(p_210575_, StructureTemplatePool::new);
   });
   public static final Codec<Holder<StructureTemplatePool>> f_210555_ = RegistryFileCodec.create(Registry.TEMPLATE_POOL_REGISTRY, f_210554_);
   private final ResourceLocation f_210558_;
   private final List<Pair<StructurePoolElement, Integer>> f_210559_;
   private final List<StructurePoolElement> f_210560_;
   private final ResourceLocation f_210561_;
   private int f_210562_ = Integer.MIN_VALUE;

   public StructureTemplatePool(ResourceLocation p_210565_, ResourceLocation p_210566_, List<Pair<StructurePoolElement, Integer>> p_210567_) {
      this.f_210558_ = p_210565_;
      this.f_210559_ = p_210567_;
      this.f_210560_ = Lists.newArrayList();

      for(Pair<StructurePoolElement, Integer> pair : p_210567_) {
         StructurePoolElement structurepoolelement = pair.getFirst();

         for(int i = 0; i < pair.getSecond(); ++i) {
            this.f_210560_.add(structurepoolelement);
         }
      }

      this.f_210561_ = p_210566_;
   }

   public StructureTemplatePool(ResourceLocation p_210569_, ResourceLocation p_210570_, List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> p_210571_, StructureTemplatePool.Projection p_210572_) {
      this.f_210558_ = p_210569_;
      this.f_210559_ = Lists.newArrayList();
      this.f_210560_ = Lists.newArrayList();

      for(Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> pair : p_210571_) {
         StructurePoolElement structurepoolelement = pair.getFirst().apply(p_210572_);
         this.f_210559_.add(Pair.of(structurepoolelement, pair.getSecond()));

         for(int i = 0; i < pair.getSecond(); ++i) {
            this.f_210560_.add(structurepoolelement);
         }
      }

      this.f_210561_ = p_210570_;
   }

   public int m_210580_(StructureManager p_210581_) {
      if (this.f_210562_ == Integer.MIN_VALUE) {
         this.f_210562_ = this.f_210560_.stream().filter((p_210577_) -> {
            return p_210577_ != EmptyPoolElement.f_210175_;
         }).mapToInt((p_210584_) -> {
            return p_210584_.m_207470_(p_210581_, BlockPos.ZERO, Rotation.NONE).getYSpan();
         }).max().orElse(0);
      }

      return this.f_210562_;
   }

   public ResourceLocation m_210573_() {
      return this.f_210561_;
   }

   public StructurePoolElement m_210585_(Random p_210586_) {
      return this.f_210560_.get(p_210586_.nextInt(this.f_210560_.size()));
   }

   public List<StructurePoolElement> m_210588_(Random p_210589_) {
      return ImmutableList.copyOf(ObjectArrays.shuffle(this.f_210560_.toArray(new StructurePoolElement[0]), p_210589_));
   }

   public ResourceLocation m_210587_() {
      return this.f_210558_;
   }

   public int m_210590_() {
      return this.f_210560_.size();
   }

   public static enum Projection implements StringRepresentable {
      TERRAIN_MATCHING("terrain_matching", ImmutableList.of(new GravityProcessor(Heightmap.Types.WORLD_SURFACE_WG, -1))),
      RIGID("rigid", ImmutableList.of());

      public static final Codec<StructureTemplatePool.Projection> f_210593_ = StringRepresentable.fromEnum(StructureTemplatePool.Projection::values, StructureTemplatePool.Projection::m_210607_);
      private static final Map<String, StructureTemplatePool.Projection> f_210594_ = Arrays.stream(values()).collect(Collectors.toMap(StructureTemplatePool.Projection::m_210604_, (p_210606_) -> {
         return p_210606_;
      }));
      private final String f_210595_;
      private final ImmutableList<StructureProcessor> f_210596_;

      private Projection(String p_210602_, ImmutableList<StructureProcessor> p_210603_) {
         this.f_210595_ = p_210602_;
         this.f_210596_ = p_210603_;
      }

      public String m_210604_() {
         return this.f_210595_;
      }

      public static StructureTemplatePool.Projection m_210607_(String p_210608_) {
         return f_210594_.get(p_210608_);
      }

      public ImmutableList<StructureProcessor> m_210609_() {
         return this.f_210596_;
      }

      public String getSerializedName() {
         return this.f_210595_;
      }
   }
}