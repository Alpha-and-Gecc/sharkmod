package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public interface ConfiguredStructureTags {
   TagKey<ConfiguredStructureFeature<?, ?>> f_207632_ = m_207643_("eye_of_ender_located");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207633_ = m_207643_("dolphin_located");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207634_ = m_207643_("on_woodland_explorer_maps");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207635_ = m_207643_("on_ocean_explorer_maps");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207636_ = m_207643_("on_treasure_maps");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207637_ = m_207643_("village");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207638_ = m_207643_("mineshaft");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207639_ = m_207643_("shipwreck");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207640_ = m_207643_("ruined_portal");
   TagKey<ConfiguredStructureFeature<?, ?>> f_207641_ = m_207643_("ocean_ruin");

   private static TagKey<ConfiguredStructureFeature<?, ?>> m_207643_(String p_207644_) {
      return TagKey.m_203882_(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(p_207644_));
   }
}