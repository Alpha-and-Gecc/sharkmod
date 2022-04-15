package net.minecraft.world.level.levelgen.structure.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.RandomSource;

public enum RandomSpreadType implements StringRepresentable {
   LINEAR("linear"),
   TRIANGULAR("triangular");

   private static final RandomSpreadType[] f_205015_ = values();
   public static final Codec<RandomSpreadType> f_205014_ = StringRepresentable.fromEnum(() -> {
      return f_205015_;
   }, RandomSpreadType::m_205027_);
   private final String f_205016_;

   private RandomSpreadType(String p_205022_) {
      this.f_205016_ = p_205022_;
   }

   public static RandomSpreadType m_205027_(String p_205028_) {
      for(RandomSpreadType randomspreadtype : f_205015_) {
         if (randomspreadtype.getSerializedName().equals(p_205028_)) {
            return randomspreadtype;
         }
      }

      throw new IllegalArgumentException("Unknown Random Spread type: " + p_205028_);
   }

   public String getSerializedName() {
      return this.f_205016_;
   }

   public int m_205024_(RandomSource p_205025_, int p_205026_) {
      int i;
      switch(this) {
      case LINEAR:
         i = p_205025_.nextInt(p_205026_);
         break;
      case TRIANGULAR:
         i = (p_205025_.nextInt(p_205026_) + p_205025_.nextInt(p_205026_)) / 2;
         break;
      default:
         throw new IncompatibleClassChangeError();
      }

      return i;
   }
}