package net.minecraft.world.level.levelgen.structure.pools;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public abstract class StructurePoolElement {
   public static final Codec<StructurePoolElement> f_210468_ = Registry.STRUCTURE_POOL_ELEMENT.byNameCodec().dispatch("element_type", StructurePoolElement::m_207234_, StructurePoolElementType::m_210553_);
   @Nullable
   private volatile StructureTemplatePool.Projection f_210467_;

   protected static <E extends StructurePoolElement> RecordCodecBuilder<E, StructureTemplatePool.Projection> m_210538_() {
      return StructureTemplatePool.Projection.f_210593_.fieldOf("projection").forGetter(StructurePoolElement::m_210539_);
   }

   protected StructurePoolElement(StructureTemplatePool.Projection p_210471_) {
      this.f_210467_ = p_210471_;
   }

   public abstract Vec3i m_207466_(StructureManager p_210493_, Rotation p_210494_);

   public abstract List<StructureTemplate.StructureBlockInfo> m_207245_(StructureManager p_210498_, BlockPos p_210499_, Rotation p_210500_, Random p_210501_);

   public abstract BoundingBox m_207470_(StructureManager p_210495_, BlockPos p_210496_, Rotation p_210497_);

   public abstract boolean m_207251_(StructureManager p_210483_, WorldGenLevel p_210484_, StructureFeatureManager p_210485_, ChunkGenerator p_210486_, BlockPos p_210487_, BlockPos p_210488_, Rotation p_210489_, BoundingBox p_210490_, Random p_210491_, boolean p_210492_);

   public abstract StructurePoolElementType<?> m_207234_();

   public void m_210472_(LevelAccessor p_210473_, StructureTemplate.StructureBlockInfo p_210474_, BlockPos p_210475_, Rotation p_210476_, Random p_210477_, BoundingBox p_210478_) {
   }

   public StructurePoolElement m_207247_(StructureTemplatePool.Projection p_210479_) {
      this.f_210467_ = p_210479_;
      return this;
   }

   public StructureTemplatePool.Projection m_210539_() {
      StructureTemplatePool.Projection structuretemplatepool$projection = this.f_210467_;
      if (structuretemplatepool$projection == null) {
         throw new IllegalStateException();
      } else {
         return structuretemplatepool$projection;
      }
   }

   public int m_210540_() {
      return 1;
   }

   public static Function<StructureTemplatePool.Projection, EmptyPoolElement> m_210541_() {
      return (p_210525_) -> {
         return EmptyPoolElement.f_210175_;
      };
   }

   public static Function<StructureTemplatePool.Projection, LegacySinglePoolElement> m_210507_(String p_210508_) {
      return (p_210530_) -> {
         return new LegacySinglePoolElement(Either.left(new ResourceLocation(p_210508_)), ProcessorLists.EMPTY, p_210530_);
      };
   }

   public static Function<StructureTemplatePool.Projection, LegacySinglePoolElement> m_210512_(String p_210513_, Holder<StructureProcessorList> p_210514_) {
      return (p_210537_) -> {
         return new LegacySinglePoolElement(Either.left(new ResourceLocation(p_210513_)), p_210514_, p_210537_);
      };
   }

   public static Function<StructureTemplatePool.Projection, SinglePoolElement> m_210526_(String p_210527_) {
      return (p_210511_) -> {
         return new SinglePoolElement(Either.left(new ResourceLocation(p_210527_)), ProcessorLists.EMPTY, p_210511_);
      };
   }

   public static Function<StructureTemplatePool.Projection, SinglePoolElement> m_210531_(String p_210532_, Holder<StructureProcessorList> p_210533_) {
      return (p_210518_) -> {
         return new SinglePoolElement(Either.left(new ResourceLocation(p_210532_)), p_210533_, p_210518_);
      };
   }

   public static Function<StructureTemplatePool.Projection, FeaturePoolElement> m_210502_(Holder<PlacedFeature> p_210503_) {
      return (p_210506_) -> {
         return new FeaturePoolElement(p_210503_, p_210506_);
      };
   }

   public static Function<StructureTemplatePool.Projection, ListPoolElement> m_210519_(List<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>> p_210520_) {
      return (p_210523_) -> {
         return new ListPoolElement(p_210520_.stream().map((p_210482_) -> {
            return p_210482_.apply(p_210523_);
         }).collect(Collectors.toList()), p_210523_);
      };
   }
}