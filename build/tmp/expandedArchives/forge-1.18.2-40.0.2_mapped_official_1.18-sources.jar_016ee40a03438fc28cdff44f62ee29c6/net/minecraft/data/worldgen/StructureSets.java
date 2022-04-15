package net.minecraft.data.worldgen;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

public interface StructureSets {
   Holder<StructureSet> f_211109_ = m_211128_(BuiltinStructureSets.f_209820_, new StructureSet(List.of(StructureSet.m_210015_(StructureFeatures.VILLAGE_PLAINS), StructureSet.m_210015_(StructureFeatures.VILLAGE_DESERT), StructureSet.m_210015_(StructureFeatures.VILLAGE_SAVANNA), StructureSet.m_210015_(StructureFeatures.VILLAGE_SNOWY), StructureSet.m_210015_(StructureFeatures.VILLAGE_TAIGA)), new RandomSpreadStructurePlacement(34, 8, RandomSpreadType.LINEAR, 10387312)));
   Holder<StructureSet> f_211110_ = m_211131_(BuiltinStructureSets.f_209821_, StructureFeatures.DESERT_PYRAMID, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357617));
   Holder<StructureSet> f_211111_ = m_211131_(BuiltinStructureSets.f_209822_, StructureFeatures.IGLOO, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357618));
   Holder<StructureSet> f_211112_ = m_211131_(BuiltinStructureSets.f_209823_, StructureFeatures.JUNGLE_TEMPLE, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357619));
   Holder<StructureSet> f_211113_ = m_211131_(BuiltinStructureSets.f_209824_, StructureFeatures.SWAMP_HUT, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357620));
   Holder<StructureSet> f_211114_ = m_211131_(BuiltinStructureSets.f_209825_, StructureFeatures.PILLAGER_OUTPOST, new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 165745296));
   Holder<StructureSet> f_211115_ = m_211131_(BuiltinStructureSets.f_209826_, StructureFeatures.OCEAN_MONUMENT, new RandomSpreadStructurePlacement(32, 5, RandomSpreadType.TRIANGULAR, 10387313));
   Holder<StructureSet> f_211116_ = m_211131_(BuiltinStructureSets.f_209827_, StructureFeatures.WOODLAND_MANSION, new RandomSpreadStructurePlacement(80, 20, RandomSpreadType.TRIANGULAR, 10387319));
   Holder<StructureSet> f_211117_ = m_211131_(BuiltinStructureSets.f_209828_, StructureFeatures.BURIED_TREASURE, new RandomSpreadStructurePlacement(1, 0, RandomSpreadType.LINEAR, 0, new Vec3i(9, 0, 9)));
   Holder<StructureSet> f_211118_ = m_211128_(BuiltinStructureSets.f_209829_, new StructureSet(List.of(StructureSet.m_210015_(StructureFeatures.MINESHAFT), StructureSet.m_210015_(StructureFeatures.MINESHAFT_MESA)), new RandomSpreadStructurePlacement(1, 0, RandomSpreadType.LINEAR, 0)));
   Holder<StructureSet> f_211119_ = m_211128_(BuiltinStructureSets.f_209830_, new StructureSet(List.of(StructureSet.m_210015_(StructureFeatures.RUINED_PORTAL_STANDARD), StructureSet.m_210015_(StructureFeatures.RUINED_PORTAL_DESERT), StructureSet.m_210015_(StructureFeatures.RUINED_PORTAL_JUNGLE), StructureSet.m_210015_(StructureFeatures.RUINED_PORTAL_SWAMP), StructureSet.m_210015_(StructureFeatures.RUINED_PORTAL_MOUNTAIN), StructureSet.m_210015_(StructureFeatures.RUINED_PORTAL_OCEAN), StructureSet.m_210015_(StructureFeatures.RUINED_PORTAL_NETHER)), new RandomSpreadStructurePlacement(40, 15, RandomSpreadType.LINEAR, 34222645)));
   Holder<StructureSet> f_211120_ = m_211128_(BuiltinStructureSets.f_209831_, new StructureSet(List.of(StructureSet.m_210015_(StructureFeatures.SHIPWRECK), StructureSet.m_210015_(StructureFeatures.SHIPWRECK_BEACHED)), new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 165745295)));
   Holder<StructureSet> f_211121_ = m_211128_(BuiltinStructureSets.f_209832_, new StructureSet(List.of(StructureSet.m_210015_(StructureFeatures.OCEAN_RUIN_COLD), StructureSet.m_210015_(StructureFeatures.OCEAN_RUIN_WARM)), new RandomSpreadStructurePlacement(20, 8, RandomSpreadType.LINEAR, 14357621)));
   Holder<StructureSet> f_211122_ = m_211128_(BuiltinStructureSets.f_209833_, new StructureSet(List.of(StructureSet.m_210017_(StructureFeatures.f_211105_, 2), StructureSet.m_210017_(StructureFeatures.BASTION_REMNANT, 3)), new RandomSpreadStructurePlacement(27, 4, RandomSpreadType.LINEAR, 30084232)));
   Holder<StructureSet> f_211123_ = m_211131_(BuiltinStructureSets.f_209834_, StructureFeatures.NETHER_FOSSIL, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 14357921));
   Holder<StructureSet> f_211124_ = m_211131_(BuiltinStructureSets.f_209835_, StructureFeatures.END_CITY, new RandomSpreadStructurePlacement(20, 11, RandomSpreadType.TRIANGULAR, 10387313));
   Holder<StructureSet> f_211125_ = m_211131_(BuiltinStructureSets.f_209836_, StructureFeatures.STRONGHOLD, new ConcentricRingsStructurePlacement(32, 3, 128));

   static Holder<StructureSet> m_211127_() {
      return BuiltinRegistries.f_211084_.m_203611_().iterator().next();
   }

   static Holder<StructureSet> m_211128_(ResourceKey<StructureSet> p_211129_, StructureSet p_211130_) {
      return BuiltinRegistries.m_206384_(BuiltinRegistries.f_211084_, p_211129_, p_211130_);
   }

   static Holder<StructureSet> m_211131_(ResourceKey<StructureSet> p_211132_, Holder<ConfiguredStructureFeature<?, ?>> p_211133_, StructurePlacement p_211134_) {
      return m_211128_(p_211132_, new StructureSet(p_211133_, p_211134_));
   }
}