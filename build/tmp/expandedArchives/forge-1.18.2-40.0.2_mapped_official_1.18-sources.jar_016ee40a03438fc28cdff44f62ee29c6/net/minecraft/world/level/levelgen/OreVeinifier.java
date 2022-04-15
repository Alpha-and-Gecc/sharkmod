package net.minecraft.world.level.levelgen;

import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class OreVeinifier {
   private static final float f_209650_ = 0.4F;
   private static final int f_209651_ = 20;
   private static final double f_209652_ = 0.2D;
   private static final float f_209653_ = 0.7F;
   private static final float f_209654_ = 0.1F;
   private static final float f_209655_ = 0.3F;
   private static final float f_209656_ = 0.6F;
   private static final float f_209657_ = 0.02F;
   private static final float f_209658_ = -0.3F;

   private OreVeinifier() {
   }

   protected static NoiseChunk.BlockStateFiller m_209667_(DensityFunction p_209668_, DensityFunction p_209669_, DensityFunction p_209670_, PositionalRandomFactory p_209671_) {
      BlockState blockstate = null;
      return (p_209666_) -> {
         double d0 = p_209668_.m_207386_(p_209666_);
         int i = p_209666_.m_207114_();
         OreVeinifier.VeinType oreveinifier$veintype = d0 > 0.0D ? OreVeinifier.VeinType.COPPER : OreVeinifier.VeinType.IRON;
         double d1 = Math.abs(d0);
         int j = oreveinifier$veintype.f_209675_ - i;
         int k = i - oreveinifier$veintype.f_209674_;
         if (k >= 0 && j >= 0) {
            int l = Math.min(j, k);
            double d2 = Mth.clampedMap((double)l, 0.0D, 20.0D, -0.2D, 0.0D);
            if (d1 + d2 < (double)0.4F) {
               return blockstate;
            } else {
               RandomSource randomsource = p_209671_.at(p_209666_.m_207115_(), i, p_209666_.m_207113_());
               if (randomsource.nextFloat() > 0.7F) {
                  return blockstate;
               } else if (p_209669_.m_207386_(p_209666_) >= 0.0D) {
                  return blockstate;
               } else {
                  double d3 = Mth.clampedMap(d1, (double)0.4F, (double)0.6F, (double)0.1F, (double)0.3F);
                  if ((double)randomsource.nextFloat() < d3 && p_209670_.m_207386_(p_209666_) > (double)-0.3F) {
                     return randomsource.nextFloat() < 0.02F ? oreveinifier$veintype.f_209677_ : oreveinifier$veintype.f_209676_;
                  } else {
                     return oreveinifier$veintype.f_209678_;
                  }
               }
            }
         } else {
            return blockstate;
         }
      };
   }

   protected static enum VeinType {
      COPPER(Blocks.COPPER_ORE.defaultBlockState(), Blocks.RAW_COPPER_BLOCK.defaultBlockState(), Blocks.GRANITE.defaultBlockState(), 0, 50),
      IRON(Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), Blocks.RAW_IRON_BLOCK.defaultBlockState(), Blocks.TUFF.defaultBlockState(), -60, -8);

      final BlockState f_209676_;
      final BlockState f_209677_;
      final BlockState f_209678_;
      protected final int f_209674_;
      protected final int f_209675_;

      private VeinType(BlockState p_209684_, BlockState p_209685_, BlockState p_209686_, int p_209687_, int p_209688_) {
         this.f_209676_ = p_209684_;
         this.f_209677_ = p_209685_;
         this.f_209678_ = p_209686_;
         this.f_209674_ = p_209687_;
         this.f_209675_ = p_209688_;
      }
   }
}