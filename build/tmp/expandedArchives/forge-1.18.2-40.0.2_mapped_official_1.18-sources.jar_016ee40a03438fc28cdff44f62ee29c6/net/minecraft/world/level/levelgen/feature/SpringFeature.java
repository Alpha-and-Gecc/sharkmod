package net.minecraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;

public class SpringFeature extends Feature<SpringConfiguration> {
   public SpringFeature(Codec<SpringConfiguration> p_66914_) {
      super(p_66914_);
   }

   public boolean place(FeaturePlaceContext<SpringConfiguration> p_160404_) {
      SpringConfiguration springconfiguration = p_160404_.config();
      WorldGenLevel worldgenlevel = p_160404_.level();
      BlockPos blockpos = p_160404_.origin();
      if (!worldgenlevel.getBlockState(blockpos.above()).m_204341_(springconfiguration.validBlocks)) {
         return false;
      } else if (springconfiguration.requiresBlockBelow && !worldgenlevel.getBlockState(blockpos.below()).m_204341_(springconfiguration.validBlocks)) {
         return false;
      } else {
         BlockState blockstate = worldgenlevel.getBlockState(blockpos);
         if (!blockstate.isAir() && !blockstate.m_204341_(springconfiguration.validBlocks)) {
            return false;
         } else {
            int i = 0;
            int j = 0;
            if (worldgenlevel.getBlockState(blockpos.west()).m_204341_(springconfiguration.validBlocks)) {
               ++j;
            }

            if (worldgenlevel.getBlockState(blockpos.east()).m_204341_(springconfiguration.validBlocks)) {
               ++j;
            }

            if (worldgenlevel.getBlockState(blockpos.north()).m_204341_(springconfiguration.validBlocks)) {
               ++j;
            }

            if (worldgenlevel.getBlockState(blockpos.south()).m_204341_(springconfiguration.validBlocks)) {
               ++j;
            }

            if (worldgenlevel.getBlockState(blockpos.below()).m_204341_(springconfiguration.validBlocks)) {
               ++j;
            }

            int k = 0;
            if (worldgenlevel.isEmptyBlock(blockpos.west())) {
               ++k;
            }

            if (worldgenlevel.isEmptyBlock(blockpos.east())) {
               ++k;
            }

            if (worldgenlevel.isEmptyBlock(blockpos.north())) {
               ++k;
            }

            if (worldgenlevel.isEmptyBlock(blockpos.south())) {
               ++k;
            }

            if (worldgenlevel.isEmptyBlock(blockpos.below())) {
               ++k;
            }

            if (j == springconfiguration.rockCount && k == springconfiguration.holeCount) {
               worldgenlevel.setBlock(blockpos, springconfiguration.state.createLegacyBlock(), 2);
               worldgenlevel.scheduleTick(blockpos, springconfiguration.state.getType(), 0);
               ++i;
            }

            return i > 0;
         }
      }
   }
}