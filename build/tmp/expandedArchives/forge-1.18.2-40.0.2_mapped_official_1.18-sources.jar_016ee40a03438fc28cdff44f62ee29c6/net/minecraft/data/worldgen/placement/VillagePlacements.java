package net.minecraft.data.worldgen.placement;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.PileFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class VillagePlacements {
   public static final Holder<PlacedFeature> PILE_HAY_VILLAGE = PlacementUtils.m_206513_("pile_hay", PileFeatures.PILE_HAY);
   public static final Holder<PlacedFeature> PILE_MELON_VILLAGE = PlacementUtils.m_206513_("pile_melon", PileFeatures.PILE_MELON);
   public static final Holder<PlacedFeature> PILE_SNOW_VILLAGE = PlacementUtils.m_206513_("pile_snow", PileFeatures.PILE_SNOW);
   public static final Holder<PlacedFeature> PILE_ICE_VILLAGE = PlacementUtils.m_206513_("pile_ice", PileFeatures.PILE_ICE);
   public static final Holder<PlacedFeature> PILE_PUMPKIN_VILLAGE = PlacementUtils.m_206513_("pile_pumpkin", PileFeatures.PILE_PUMPKIN);
   public static final Holder<PlacedFeature> OAK_VILLAGE = PlacementUtils.m_206513_("oak", TreeFeatures.OAK, PlacementUtils.m_206493_(Blocks.OAK_SAPLING));
   public static final Holder<PlacedFeature> ACACIA_VILLAGE = PlacementUtils.m_206513_("acacia", TreeFeatures.ACACIA, PlacementUtils.m_206493_(Blocks.ACACIA_SAPLING));
   public static final Holder<PlacedFeature> SPRUCE_VILLAGE = PlacementUtils.m_206513_("spruce", TreeFeatures.SPRUCE, PlacementUtils.m_206493_(Blocks.SPRUCE_SAPLING));
   public static final Holder<PlacedFeature> PINE_VILLAGE = PlacementUtils.m_206513_("pine", TreeFeatures.PINE, PlacementUtils.m_206493_(Blocks.SPRUCE_SAPLING));
   public static final Holder<PlacedFeature> PATCH_CACTUS_VILLAGE = PlacementUtils.m_206513_("patch_cactus", VegetationFeatures.PATCH_CACTUS);
   public static final Holder<PlacedFeature> FLOWER_PLAIN_VILLAGE = PlacementUtils.m_206513_("flower_plain", VegetationFeatures.FLOWER_PLAIN);
   public static final Holder<PlacedFeature> PATCH_TAIGA_GRASS_VILLAGE = PlacementUtils.m_206513_("patch_taiga_grass", VegetationFeatures.PATCH_TAIGA_GRASS);
   public static final Holder<PlacedFeature> PATCH_BERRY_BUSH_VILLAGE = PlacementUtils.m_206513_("patch_berry_bush", VegetationFeatures.PATCH_BERRY_BUSH);
}