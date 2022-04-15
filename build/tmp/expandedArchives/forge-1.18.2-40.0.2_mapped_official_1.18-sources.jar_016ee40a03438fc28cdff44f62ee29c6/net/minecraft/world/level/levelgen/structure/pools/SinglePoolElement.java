package net.minecraft.world.level.levelgen.structure.pools;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.JigsawReplacementProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class SinglePoolElement extends StructurePoolElement {
   private static final Codec<Either<ResourceLocation, StructureTemplate>> f_210409_ = Codec.of(SinglePoolElement::m_210424_, ResourceLocation.CODEC.map(Either::left));
   public static final Codec<SinglePoolElement> f_210410_ = RecordCodecBuilder.create((p_210429_) -> {
      return p_210429_.group(m_210465_(), m_210462_(), m_210538_()).apply(p_210429_, SinglePoolElement::new);
   });
   protected final Either<ResourceLocation, StructureTemplate> f_210411_;
   protected final Holder<StructureProcessorList> f_210412_;

   private static <T> DataResult<T> m_210424_(Either<ResourceLocation, StructureTemplate> p_210425_, DynamicOps<T> p_210426_, T p_210427_) {
      Optional<ResourceLocation> optional = p_210425_.left();
      return !optional.isPresent() ? DataResult.error("Can not serialize a runtime pool element") : ResourceLocation.CODEC.encode(optional.get(), p_210426_, p_210427_);
   }

   protected static <E extends SinglePoolElement> RecordCodecBuilder<E, Holder<StructureProcessorList>> m_210462_() {
      return StructureProcessorType.LIST_CODEC.fieldOf("processors").forGetter((p_210464_) -> {
         return p_210464_.f_210412_;
      });
   }

   protected static <E extends SinglePoolElement> RecordCodecBuilder<E, Either<ResourceLocation, StructureTemplate>> m_210465_() {
      return f_210409_.fieldOf("location").forGetter((p_210431_) -> {
         return p_210431_.f_210411_;
      });
   }

   protected SinglePoolElement(Either<ResourceLocation, StructureTemplate> p_210415_, Holder<StructureProcessorList> p_210416_, StructureTemplatePool.Projection p_210417_) {
      super(p_210417_);
      this.f_210411_ = p_210415_;
      this.f_210412_ = p_210416_;
   }

   public SinglePoolElement(StructureTemplate p_210419_) {
      this(Either.right(p_210419_), ProcessorLists.EMPTY, StructureTemplatePool.Projection.RIGID);
   }

   public Vec3i m_207466_(StructureManager p_210446_, Rotation p_210447_) {
      StructureTemplate structuretemplate = this.m_210432_(p_210446_);
      return structuretemplate.getSize(p_210447_);
   }

   private StructureTemplate m_210432_(StructureManager p_210433_) {
      return this.f_210411_.map(p_210433_::getOrCreate, Function.identity());
   }

   public List<StructureTemplate.StructureBlockInfo> m_210457_(StructureManager p_210458_, BlockPos p_210459_, Rotation p_210460_, boolean p_210461_) {
      StructureTemplate structuretemplate = this.m_210432_(p_210458_);
      List<StructureTemplate.StructureBlockInfo> list = structuretemplate.filterBlocks(p_210459_, (new StructurePlaceSettings()).setRotation(p_210460_), Blocks.STRUCTURE_BLOCK, p_210461_);
      List<StructureTemplate.StructureBlockInfo> list1 = Lists.newArrayList();

      for(StructureTemplate.StructureBlockInfo structuretemplate$structureblockinfo : list) {
         if (structuretemplate$structureblockinfo.nbt != null) {
            StructureMode structuremode = StructureMode.valueOf(structuretemplate$structureblockinfo.nbt.getString("mode"));
            if (structuremode == StructureMode.DATA) {
               list1.add(structuretemplate$structureblockinfo);
            }
         }
      }

      return list1;
   }

   public List<StructureTemplate.StructureBlockInfo> m_207245_(StructureManager p_210453_, BlockPos p_210454_, Rotation p_210455_, Random p_210456_) {
      StructureTemplate structuretemplate = this.m_210432_(p_210453_);
      List<StructureTemplate.StructureBlockInfo> list = structuretemplate.filterBlocks(p_210454_, (new StructurePlaceSettings()).setRotation(p_210455_), Blocks.JIGSAW, true);
      Collections.shuffle(list, p_210456_);
      return list;
   }

   public BoundingBox m_207470_(StructureManager p_210449_, BlockPos p_210450_, Rotation p_210451_) {
      StructureTemplate structuretemplate = this.m_210432_(p_210449_);
      return structuretemplate.getBoundingBox((new StructurePlaceSettings()).setRotation(p_210451_), p_210450_);
   }

   public boolean m_207251_(StructureManager p_210435_, WorldGenLevel p_210436_, StructureFeatureManager p_210437_, ChunkGenerator p_210438_, BlockPos p_210439_, BlockPos p_210440_, Rotation p_210441_, BoundingBox p_210442_, Random p_210443_, boolean p_210444_) {
      StructureTemplate structuretemplate = this.m_210432_(p_210435_);
      StructurePlaceSettings structureplacesettings = this.m_207169_(p_210441_, p_210442_, p_210444_);
      if (!structuretemplate.placeInWorld(p_210436_, p_210439_, p_210440_, structureplacesettings, p_210443_, 18)) {
         return false;
      } else {
         for(StructureTemplate.StructureBlockInfo structuretemplate$structureblockinfo : StructureTemplate.processBlockInfos(p_210436_, p_210439_, p_210440_, structureplacesettings, this.m_210457_(p_210435_, p_210439_, p_210441_, false))) {
            this.m_210472_(p_210436_, structuretemplate$structureblockinfo, p_210439_, p_210441_, p_210443_, p_210442_);
         }

         return true;
      }
   }

   protected StructurePlaceSettings m_207169_(Rotation p_210421_, BoundingBox p_210422_, boolean p_210423_) {
      StructurePlaceSettings structureplacesettings = new StructurePlaceSettings();
      structureplacesettings.setBoundingBox(p_210422_);
      structureplacesettings.setRotation(p_210421_);
      structureplacesettings.setKnownShape(true);
      structureplacesettings.setIgnoreEntities(false);
      structureplacesettings.addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
      structureplacesettings.setFinalizeEntities(true);
      if (!p_210423_) {
         structureplacesettings.addProcessor(JigsawReplacementProcessor.INSTANCE);
      }

      this.f_210412_.m_203334_().list().forEach(structureplacesettings::addProcessor);
      this.m_210539_().m_210609_().forEach(structureplacesettings::addProcessor);
      return structureplacesettings;
   }

   public StructurePoolElementType<?> m_207234_() {
      return StructurePoolElementType.f_210542_;
   }

   public String toString() {
      return "Single[" + this.f_210411_ + "]";
   }
}