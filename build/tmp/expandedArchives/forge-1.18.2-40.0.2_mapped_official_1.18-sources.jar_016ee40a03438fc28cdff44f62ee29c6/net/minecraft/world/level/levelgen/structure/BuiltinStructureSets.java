package net.minecraft.world.level.levelgen.structure;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public interface BuiltinStructureSets {
   ResourceKey<StructureSet> f_209820_ = m_209838_("villages");
   ResourceKey<StructureSet> f_209821_ = m_209838_("desert_pyramids");
   ResourceKey<StructureSet> f_209822_ = m_209838_("igloos");
   ResourceKey<StructureSet> f_209823_ = m_209838_("jungle_temples");
   ResourceKey<StructureSet> f_209824_ = m_209838_("swamp_huts");
   ResourceKey<StructureSet> f_209825_ = m_209838_("pillager_outposts");
   ResourceKey<StructureSet> f_209826_ = m_209838_("ocean_monuments");
   ResourceKey<StructureSet> f_209827_ = m_209838_("woodland_mansions");
   ResourceKey<StructureSet> f_209828_ = m_209838_("buried_treasures");
   ResourceKey<StructureSet> f_209829_ = m_209838_("mineshafts");
   ResourceKey<StructureSet> f_209830_ = m_209838_("ruined_portals");
   ResourceKey<StructureSet> f_209831_ = m_209838_("shipwrecks");
   ResourceKey<StructureSet> f_209832_ = m_209838_("ocean_ruins");
   ResourceKey<StructureSet> f_209833_ = m_209838_("nether_complexes");
   ResourceKey<StructureSet> f_209834_ = m_209838_("nether_fossils");
   ResourceKey<StructureSet> f_209835_ = m_209838_("end_cities");
   ResourceKey<StructureSet> f_209836_ = m_209838_("strongholds");

   private static ResourceKey<StructureSet> m_209838_(String p_209839_) {
      return ResourceKey.create(Registry.f_211073_, new ResourceLocation(p_209839_));
   }
}