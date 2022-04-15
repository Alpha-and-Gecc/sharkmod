package net.minecraft.world.entity.ai.goal;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;

public class ClimbOnTopOfPowderSnowGoal extends Goal {
   private final Mob f_204052_;
   private final Level f_204053_;

   public ClimbOnTopOfPowderSnowGoal(Mob p_204055_, Level p_204056_) {
      this.f_204052_ = p_204055_;
      this.f_204053_ = p_204056_;
      this.setFlags(EnumSet.of(Goal.Flag.JUMP));
   }

   public boolean canUse() {
      boolean flag = this.f_204052_.wasInPowderSnow || this.f_204052_.isInPowderSnow;
      if (flag && this.f_204052_.getType().m_204039_(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)) {
         BlockPos blockpos = this.f_204052_.blockPosition().above();
         BlockState blockstate = this.f_204053_.getBlockState(blockpos);
         return blockstate.is(Blocks.POWDER_SNOW) || blockstate.getCollisionShape(this.f_204053_, blockpos) == Shapes.empty();
      } else {
         return false;
      }
   }

   public boolean requiresUpdateEveryTick() {
      return true;
   }

   public void tick() {
      this.f_204052_.getJumpControl().jump();
   }
}