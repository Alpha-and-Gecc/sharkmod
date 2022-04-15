package net.minecraft.data.worldgen;

import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.MineshaftFeature;
import net.minecraft.world.level.levelgen.feature.NetherFortressFeature;
import net.minecraft.world.level.levelgen.feature.RuinedPortalFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.MineshaftConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OceanRuinConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RuinedPortalConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ShipwreckConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.OceanRuinFeature;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;

public class StructureFeatures {
   public static final Holder<ConfiguredStructureFeature<?, ?>> PILLAGER_OUTPOST = m_211106_(BuiltinStructures.f_209845_, StructureFeature.PILLAGER_OUTPOST.m_209773_(new JigsawConfiguration(PillagerOutpostPools.START, 7), BiomeTags.f_207622_, true, Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 1, 1, 1))))));
   public static final Holder<ConfiguredStructureFeature<?, ?>> MINESHAFT = m_211106_(BuiltinStructures.f_209846_, StructureFeature.MINESHAFT.m_209762_(new MineshaftConfiguration(0.004F, MineshaftFeature.Type.NORMAL), BiomeTags.f_207617_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> MINESHAFT_MESA = m_211106_(BuiltinStructures.f_209847_, StructureFeature.MINESHAFT.m_209762_(new MineshaftConfiguration(0.004F, MineshaftFeature.Type.MESA), BiomeTags.f_207618_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> WOODLAND_MANSION = m_211106_(BuiltinStructures.f_209848_, StructureFeature.WOODLAND_MANSION.m_209762_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207595_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> JUNGLE_TEMPLE = m_211106_(BuiltinStructures.f_209849_, StructureFeature.JUNGLE_TEMPLE.m_209762_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207616_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> DESERT_PYRAMID = m_211106_(BuiltinStructures.f_209850_, StructureFeature.DESERT_PYRAMID.m_209762_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207614_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> IGLOO = m_211106_(BuiltinStructures.f_209851_, StructureFeature.IGLOO.m_209762_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207615_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> SHIPWRECK = m_211106_(BuiltinStructures.f_209852_, StructureFeature.SHIPWRECK.m_209762_(new ShipwreckConfiguration(false), BiomeTags.f_207588_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> SHIPWRECK_BEACHED = m_211106_(BuiltinStructures.f_209853_, StructureFeature.SHIPWRECK.m_209762_(new ShipwreckConfiguration(true), BiomeTags.f_207587_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> SWAMP_HUT = m_211106_(BuiltinStructures.f_209854_, StructureFeature.SWAMP_HUT.m_209765_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207589_, Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1, 1))), MobCategory.CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.CAT, 1, 1, 1))))));
   public static final Holder<ConfiguredStructureFeature<?, ?>> STRONGHOLD = m_211106_(BuiltinStructures.f_209855_, StructureFeature.STRONGHOLD.m_209769_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207596_, true));
   public static final Holder<ConfiguredStructureFeature<?, ?>> OCEAN_MONUMENT = m_211106_(BuiltinStructures.f_209856_, StructureFeature.OCEAN_MONUMENT.m_209765_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207619_, Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(EntityType.GUARDIAN, 1, 2, 4))), MobCategory.UNDERGROUND_WATER_CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobSpawnSettings.EMPTY_MOB_LIST), MobCategory.AXOLOTLS, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobSpawnSettings.EMPTY_MOB_LIST))));
   public static final Holder<ConfiguredStructureFeature<?, ?>> OCEAN_RUIN_COLD = m_211106_(BuiltinStructures.f_209857_, StructureFeature.OCEAN_RUIN.m_209762_(new OceanRuinConfiguration(OceanRuinFeature.Type.COLD, 0.3F, 0.9F), BiomeTags.f_207620_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> OCEAN_RUIN_WARM = m_211106_(BuiltinStructures.f_209858_, StructureFeature.OCEAN_RUIN.m_209762_(new OceanRuinConfiguration(OceanRuinFeature.Type.WARM, 0.3F, 0.9F), BiomeTags.f_207621_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> f_211105_ = m_211106_(BuiltinStructures.f_209859_, StructureFeature.f_209756_.m_209765_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207597_, Map.of(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, NetherFortressFeature.FORTRESS_ENEMIES))));
   public static final Holder<ConfiguredStructureFeature<?, ?>> NETHER_FOSSIL = m_211106_(BuiltinStructures.f_209860_, StructureFeature.NETHER_FOSSIL.m_209769_(new RangeConfiguration(UniformHeight.of(VerticalAnchor.absolute(32), VerticalAnchor.belowTop(2))), BiomeTags.f_207598_, true));
   public static final Holder<ConfiguredStructureFeature<?, ?>> END_CITY = m_211106_(BuiltinStructures.f_209861_, StructureFeature.END_CITY.m_209762_(NoneFeatureConfiguration.INSTANCE, BiomeTags.f_207601_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> BURIED_TREASURE = m_211106_(BuiltinStructures.f_209862_, StructureFeature.BURIED_TREASURE.m_209762_(new ProbabilityFeatureConfiguration(0.01F), BiomeTags.f_207613_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> BASTION_REMNANT = m_211106_(BuiltinStructures.f_209863_, StructureFeature.BASTION_REMNANT.m_209762_(new JigsawConfiguration(BastionPieces.START, 6), BiomeTags.f_207599_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> VILLAGE_PLAINS = m_211106_(BuiltinStructures.f_209864_, StructureFeature.VILLAGE.m_209769_(new JigsawConfiguration(PlainVillagePools.START, 6), BiomeTags.f_207591_, true));
   public static final Holder<ConfiguredStructureFeature<?, ?>> VILLAGE_DESERT = m_211106_(BuiltinStructures.f_209865_, StructureFeature.VILLAGE.m_209769_(new JigsawConfiguration(DesertVillagePools.START, 6), BiomeTags.f_207590_, true));
   public static final Holder<ConfiguredStructureFeature<?, ?>> VILLAGE_SAVANNA = m_211106_(BuiltinStructures.f_209866_, StructureFeature.VILLAGE.m_209769_(new JigsawConfiguration(SavannaVillagePools.START, 6), BiomeTags.f_207592_, true));
   public static final Holder<ConfiguredStructureFeature<?, ?>> VILLAGE_SNOWY = m_211106_(BuiltinStructures.f_209867_, StructureFeature.VILLAGE.m_209769_(new JigsawConfiguration(SnowyVillagePools.START, 6), BiomeTags.f_207593_, true));
   public static final Holder<ConfiguredStructureFeature<?, ?>> VILLAGE_TAIGA = m_211106_(BuiltinStructures.f_209868_, StructureFeature.VILLAGE.m_209769_(new JigsawConfiguration(TaigaVillagePools.START, 6), BiomeTags.f_207594_, true));
   public static final Holder<ConfiguredStructureFeature<?, ?>> RUINED_PORTAL_STANDARD = m_211106_(BuiltinStructures.f_209869_, StructureFeature.RUINED_PORTAL.m_209762_(new RuinedPortalConfiguration(RuinedPortalFeature.Type.STANDARD), BiomeTags.f_207586_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> RUINED_PORTAL_DESERT = m_211106_(BuiltinStructures.f_209870_, StructureFeature.RUINED_PORTAL.m_209762_(new RuinedPortalConfiguration(RuinedPortalFeature.Type.DESERT), BiomeTags.f_207623_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> RUINED_PORTAL_JUNGLE = m_211106_(BuiltinStructures.f_209840_, StructureFeature.RUINED_PORTAL.m_209762_(new RuinedPortalConfiguration(RuinedPortalFeature.Type.JUNGLE), BiomeTags.f_207624_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> RUINED_PORTAL_SWAMP = m_211106_(BuiltinStructures.f_209841_, StructureFeature.RUINED_PORTAL.m_209762_(new RuinedPortalConfiguration(RuinedPortalFeature.Type.SWAMP), BiomeTags.f_207626_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> RUINED_PORTAL_MOUNTAIN = m_211106_(BuiltinStructures.f_209842_, StructureFeature.RUINED_PORTAL.m_209762_(new RuinedPortalConfiguration(RuinedPortalFeature.Type.MOUNTAIN), BiomeTags.f_207627_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> RUINED_PORTAL_OCEAN = m_211106_(BuiltinStructures.f_209843_, StructureFeature.RUINED_PORTAL.m_209762_(new RuinedPortalConfiguration(RuinedPortalFeature.Type.OCEAN), BiomeTags.f_207625_));
   public static final Holder<ConfiguredStructureFeature<?, ?>> RUINED_PORTAL_NETHER = m_211106_(BuiltinStructures.f_209844_, StructureFeature.RUINED_PORTAL.m_209762_(new RuinedPortalConfiguration(RuinedPortalFeature.Type.NETHER), BiomeTags.f_207600_));

   public static Holder<? extends ConfiguredStructureFeature<?, ?>> m_206440_() {
      return MINESHAFT;
   }

   private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> Holder<ConfiguredStructureFeature<?, ?>> m_211106_(ResourceKey<ConfiguredStructureFeature<?, ?>> p_211107_, ConfiguredStructureFeature<FC, F> p_211108_) {
      return BuiltinRegistries.m_206384_(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, p_211107_, p_211108_);
   }
}