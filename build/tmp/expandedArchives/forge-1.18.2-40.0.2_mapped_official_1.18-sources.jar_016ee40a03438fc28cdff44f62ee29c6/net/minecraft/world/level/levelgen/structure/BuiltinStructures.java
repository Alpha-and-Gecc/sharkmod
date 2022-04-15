package net.minecraft.world.level.levelgen.structure;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public interface BuiltinStructures {
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209845_ = m_209872_("pillager_outpost");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209846_ = m_209872_("mineshaft");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209847_ = m_209872_("mineshaft_mesa");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209848_ = m_209872_("mansion");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209849_ = m_209872_("jungle_pyramid");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209850_ = m_209872_("desert_pyramid");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209851_ = m_209872_("igloo");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209852_ = m_209872_("shipwreck");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209853_ = m_209872_("shipwreck_beached");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209854_ = m_209872_("swamp_hut");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209855_ = m_209872_("stronghold");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209856_ = m_209872_("monument");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209857_ = m_209872_("ocean_ruin_cold");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209858_ = m_209872_("ocean_ruin_warm");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209859_ = m_209872_("fortress");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209860_ = m_209872_("nether_fossil");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209861_ = m_209872_("end_city");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209862_ = m_209872_("buried_treasure");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209863_ = m_209872_("bastion_remnant");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209864_ = m_209872_("village_plains");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209865_ = m_209872_("village_desert");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209866_ = m_209872_("village_savanna");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209867_ = m_209872_("village_snowy");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209868_ = m_209872_("village_taiga");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209869_ = m_209872_("ruined_portal");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209870_ = m_209872_("ruined_portal_desert");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209840_ = m_209872_("ruined_portal_jungle");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209841_ = m_209872_("ruined_portal_swamp");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209842_ = m_209872_("ruined_portal_mountain");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209843_ = m_209872_("ruined_portal_ocean");
   ResourceKey<ConfiguredStructureFeature<?, ?>> f_209844_ = m_209872_("ruined_portal_nether");

   private static ResourceKey<ConfiguredStructureFeature<?, ?>> m_209872_(String p_209873_) {
      return ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(p_209873_));
   }
}