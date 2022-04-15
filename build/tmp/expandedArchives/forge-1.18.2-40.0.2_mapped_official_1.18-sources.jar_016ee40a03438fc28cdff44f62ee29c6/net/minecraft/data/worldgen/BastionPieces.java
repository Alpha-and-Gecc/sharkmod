package net.minecraft.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class BastionPieces {
   public static final Holder<StructureTemplatePool> START = Pools.m_211103_(new StructureTemplatePool(new ResourceLocation("bastion/starts"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.m_210531_("bastion/units/air_base", ProcessorLists.BASTION_GENERIC_DEGRADATION), 1), Pair.of(StructurePoolElement.m_210531_("bastion/hoglin_stable/air_base", ProcessorLists.BASTION_GENERIC_DEGRADATION), 1), Pair.of(StructurePoolElement.m_210531_("bastion/treasure/big_air_full", ProcessorLists.BASTION_GENERIC_DEGRADATION), 1), Pair.of(StructurePoolElement.m_210531_("bastion/bridge/starting_pieces/entrance_base", ProcessorLists.BASTION_GENERIC_DEGRADATION), 1)), StructureTemplatePool.Projection.RIGID));

   public static void bootstrap() {
      BastionHousingUnitsPools.bootstrap();
      BastionHoglinStablePools.bootstrap();
      BastionTreasureRoomPools.bootstrap();
      BastionBridgePools.bootstrap();
      BastionSharedPools.bootstrap();
   }
}