package net.minecraft.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class PillagerOutpostPools {
   public static final Holder<StructureTemplatePool> START = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation("pillager_outpost/base_plates"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210507_("pillager_outpost/base_plate"), 1)), StructureTemplatePool.Projection.RIGID));

   public static void bootstrap() {
   }

   static {
      Pools.m_211103_(new StructureTemplatePool(new ResourceLocation("pillager_outpost/towers"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210519_(ImmutableList.of(StructurePoolElement.m_210507_("pillager_outpost/watchtower"), StructurePoolElement.m_210512_("pillager_outpost/watchtower_overgrown", ProcessorLists.OUTPOST_ROT))), 1)), StructureTemplatePool.Projection.RIGID));
      Pools.m_211103_(new StructureTemplatePool(new ResourceLocation("pillager_outpost/feature_plates"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210507_("pillager_outpost/feature_plate"), 1)), StructureTemplatePool.Projection.TERRAIN_MATCHING));
      Pools.m_211103_(new StructureTemplatePool(new ResourceLocation("pillager_outpost/features"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210507_("pillager_outpost/feature_cage1"), 1), Pair.of(StructurePoolElement.m_210507_("pillager_outpost/feature_cage2"), 1), Pair.of(StructurePoolElement.m_210507_("pillager_outpost/feature_logs"), 1), Pair.of(StructurePoolElement.m_210507_("pillager_outpost/feature_tent1"), 1), Pair.of(StructurePoolElement.m_210507_("pillager_outpost/feature_tent2"), 1), Pair.of(StructurePoolElement.m_210507_("pillager_outpost/feature_targets"), 1), Pair.of(StructurePoolElement.m_210541_(), 6)), StructureTemplatePool.Projection.RIGID));
   }
}