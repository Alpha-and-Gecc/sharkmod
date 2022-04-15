package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class BiomeTags {
   public static final TagKey<Biome> f_207602_ = m_207630_("is_deep_ocean");
   public static final TagKey<Biome> f_207603_ = m_207630_("is_ocean");
   public static final TagKey<Biome> f_207604_ = m_207630_("is_beach");
   public static final TagKey<Biome> f_207605_ = m_207630_("is_river");
   public static final TagKey<Biome> f_207606_ = m_207630_("is_mountain");
   public static final TagKey<Biome> f_207607_ = m_207630_("is_badlands");
   public static final TagKey<Biome> f_207608_ = m_207630_("is_hill");
   public static final TagKey<Biome> f_207609_ = m_207630_("is_taiga");
   public static final TagKey<Biome> f_207610_ = m_207630_("is_jungle");
   public static final TagKey<Biome> f_207611_ = m_207630_("is_forest");
   public static final TagKey<Biome> f_207612_ = m_207630_("is_nether");
   public static final TagKey<Biome> f_207613_ = m_207630_("has_structure/buried_treasure");
   public static final TagKey<Biome> f_207614_ = m_207630_("has_structure/desert_pyramid");
   public static final TagKey<Biome> f_207615_ = m_207630_("has_structure/igloo");
   public static final TagKey<Biome> f_207616_ = m_207630_("has_structure/jungle_temple");
   public static final TagKey<Biome> f_207617_ = m_207630_("has_structure/mineshaft");
   public static final TagKey<Biome> f_207618_ = m_207630_("has_structure/mineshaft_mesa");
   public static final TagKey<Biome> f_207619_ = m_207630_("has_structure/ocean_monument");
   public static final TagKey<Biome> f_207620_ = m_207630_("has_structure/ocean_ruin_cold");
   public static final TagKey<Biome> f_207621_ = m_207630_("has_structure/ocean_ruin_warm");
   public static final TagKey<Biome> f_207622_ = m_207630_("has_structure/pillager_outpost");
   public static final TagKey<Biome> f_207623_ = m_207630_("has_structure/ruined_portal_desert");
   public static final TagKey<Biome> f_207624_ = m_207630_("has_structure/ruined_portal_jungle");
   public static final TagKey<Biome> f_207625_ = m_207630_("has_structure/ruined_portal_ocean");
   public static final TagKey<Biome> f_207626_ = m_207630_("has_structure/ruined_portal_swamp");
   public static final TagKey<Biome> f_207627_ = m_207630_("has_structure/ruined_portal_mountain");
   public static final TagKey<Biome> f_207586_ = m_207630_("has_structure/ruined_portal_standard");
   public static final TagKey<Biome> f_207587_ = m_207630_("has_structure/shipwreck_beached");
   public static final TagKey<Biome> f_207588_ = m_207630_("has_structure/shipwreck");
   public static final TagKey<Biome> f_207589_ = m_207630_("has_structure/swamp_hut");
   public static final TagKey<Biome> f_207590_ = m_207630_("has_structure/village_desert");
   public static final TagKey<Biome> f_207591_ = m_207630_("has_structure/village_plains");
   public static final TagKey<Biome> f_207592_ = m_207630_("has_structure/village_savanna");
   public static final TagKey<Biome> f_207593_ = m_207630_("has_structure/village_snowy");
   public static final TagKey<Biome> f_207594_ = m_207630_("has_structure/village_taiga");
   public static final TagKey<Biome> f_207595_ = m_207630_("has_structure/woodland_mansion");
   public static final TagKey<Biome> f_207596_ = m_207630_("has_structure/stronghold");
   public static final TagKey<Biome> f_207597_ = m_207630_("has_structure/nether_fortress");
   public static final TagKey<Biome> f_207598_ = m_207630_("has_structure/nether_fossil");
   public static final TagKey<Biome> f_207599_ = m_207630_("has_structure/bastion_remnant");
   public static final TagKey<Biome> f_207600_ = m_207630_("has_structure/ruined_portal_nether");
   public static final TagKey<Biome> f_207601_ = m_207630_("has_structure/end_city");

   private BiomeTags() {
   }

   private static TagKey<Biome> m_207630_(String p_207631_) {
      return TagKey.m_203882_(Registry.BIOME_REGISTRY, new ResourceLocation(p_207631_));
   }
}