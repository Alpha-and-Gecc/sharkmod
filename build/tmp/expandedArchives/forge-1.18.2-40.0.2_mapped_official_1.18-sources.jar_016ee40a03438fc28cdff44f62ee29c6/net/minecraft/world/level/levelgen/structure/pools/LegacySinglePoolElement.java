package net.minecraft.world.level.levelgen.structure.pools;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class LegacySinglePoolElement extends SinglePoolElement {
   public static final Codec<LegacySinglePoolElement> f_210345_ = RecordCodecBuilder.create((p_210357_) -> {
      return p_210357_.group(m_210465_(), m_210462_(), m_210538_()).apply(p_210357_, LegacySinglePoolElement::new);
   });

   protected LegacySinglePoolElement(Either<ResourceLocation, StructureTemplate> p_210348_, Holder<StructureProcessorList> p_210349_, StructureTemplatePool.Projection p_210350_) {
      super(p_210348_, p_210349_, p_210350_);
   }

   protected StructurePlaceSettings m_207169_(Rotation p_210353_, BoundingBox p_210354_, boolean p_210355_) {
      StructurePlaceSettings structureplacesettings = super.m_207169_(p_210353_, p_210354_, p_210355_);
      structureplacesettings.popProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
      structureplacesettings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
      return structureplacesettings;
   }

   public StructurePoolElementType<?> m_207234_() {
      return StructurePoolElementType.f_210546_;
   }

   public String toString() {
      return "LegacySingle[" + this.f_210411_ + "]";
   }
}