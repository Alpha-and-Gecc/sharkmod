package net.minecraft.world.level.levelgen.structure.pools;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;

public class JigsawJunction {
   private final int f_210241_;
   private final int f_210242_;
   private final int f_210243_;
   private final int f_210244_;
   private final StructureTemplatePool.Projection f_210245_;

   public JigsawJunction(int p_210247_, int p_210248_, int p_210249_, int p_210250_, StructureTemplatePool.Projection p_210251_) {
      this.f_210241_ = p_210247_;
      this.f_210242_ = p_210248_;
      this.f_210243_ = p_210249_;
      this.f_210244_ = p_210250_;
      this.f_210245_ = p_210251_;
   }

   public int m_210252_() {
      return this.f_210241_;
   }

   public int m_210257_() {
      return this.f_210242_;
   }

   public int m_210258_() {
      return this.f_210243_;
   }

   public int m_210259_() {
      return this.f_210244_;
   }

   public StructureTemplatePool.Projection m_210260_() {
      return this.f_210245_;
   }

   public <T> Dynamic<T> m_210255_(DynamicOps<T> p_210256_) {
      Builder<T, T> builder = ImmutableMap.builder();
      builder.put(p_210256_.createString("source_x"), p_210256_.createInt(this.f_210241_)).put(p_210256_.createString("source_ground_y"), p_210256_.createInt(this.f_210242_)).put(p_210256_.createString("source_z"), p_210256_.createInt(this.f_210243_)).put(p_210256_.createString("delta_y"), p_210256_.createInt(this.f_210244_)).put(p_210256_.createString("dest_proj"), p_210256_.createString(this.f_210245_.m_210604_()));
      return new Dynamic<>(p_210256_, p_210256_.createMap(builder.build()));
   }

   public static <T> JigsawJunction m_210253_(Dynamic<T> p_210254_) {
      return new JigsawJunction(p_210254_.get("source_x").asInt(0), p_210254_.get("source_ground_y").asInt(0), p_210254_.get("source_z").asInt(0), p_210254_.get("delta_y").asInt(0), StructureTemplatePool.Projection.m_210607_(p_210254_.get("dest_proj").asString("")));
   }

   public boolean equals(Object p_210262_) {
      if (this == p_210262_) {
         return true;
      } else if (p_210262_ != null && this.getClass() == p_210262_.getClass()) {
         JigsawJunction jigsawjunction = (JigsawJunction)p_210262_;
         if (this.f_210241_ != jigsawjunction.f_210241_) {
            return false;
         } else if (this.f_210243_ != jigsawjunction.f_210243_) {
            return false;
         } else if (this.f_210244_ != jigsawjunction.f_210244_) {
            return false;
         } else {
            return this.f_210245_ == jigsawjunction.f_210245_;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int i = this.f_210241_;
      i = 31 * i + this.f_210242_;
      i = 31 * i + this.f_210243_;
      i = 31 * i + this.f_210244_;
      return 31 * i + this.f_210245_.hashCode();
   }

   public String toString() {
      return "JigsawJunction{sourceX=" + this.f_210241_ + ", sourceGroundY=" + this.f_210242_ + ", sourceZ=" + this.f_210243_ + ", deltaY=" + this.f_210244_ + ", destProjection=" + this.f_210245_ + "}";
   }
}