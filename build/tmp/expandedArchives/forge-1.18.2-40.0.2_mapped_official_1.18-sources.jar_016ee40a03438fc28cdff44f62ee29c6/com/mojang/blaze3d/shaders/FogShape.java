package com.mojang.blaze3d.shaders;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum FogShape {
   SPHERE(0),
   CYLINDER(1);

   private final int f_202317_;

   private FogShape(int p_202323_) {
      this.f_202317_ = p_202323_;
   }

   public int m_202324_() {
      return this.f_202317_;
   }
}