package net.minecraft.tags;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import java.util.Optional;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public record TagKey<T>(ResourceKey<? extends Registry<T>> f_203867_, ResourceLocation f_203868_) {
   private static final Interner<TagKey<?>> f_203869_ = Interners.newStrongInterner();

   public static <T> Codec<TagKey<T>> m_203877_(ResourceKey<? extends Registry<T>> p_203878_) {
      return ResourceLocation.CODEC.xmap((p_203893_) -> {
         return m_203882_(p_203878_, p_203893_);
      }, TagKey::f_203868_);
   }

   public static <T> Codec<TagKey<T>> m_203886_(ResourceKey<? extends Registry<T>> p_203887_) {
      return Codec.STRING.comapFlatMap((p_203881_) -> {
         return p_203881_.startsWith("#") ? ResourceLocation.read(p_203881_.substring(1)).map((p_203890_) -> {
            return m_203882_(p_203887_, p_203890_);
         }) : DataResult.error("Not a tag id");
      }, (p_203876_) -> {
         return "#" + p_203876_.f_203868_;
      });
   }

   public static <T> TagKey<T> m_203882_(ResourceKey<? extends Registry<T>> p_203883_, ResourceLocation p_203884_) {
      return (TagKey<T>)f_203869_.intern(new TagKey<>(p_203883_, p_203884_));
   }

   public boolean m_207645_(ResourceKey<? extends Registry<?>> p_207646_) {
      return this.f_203867_ == p_207646_;
   }

   public <E> Optional<TagKey<E>> m_207647_(ResourceKey<? extends Registry<E>> p_207648_) {
      return this.m_207645_(p_207648_) ? Optional.of((TagKey<E>)this) : Optional.empty();
   }

   public String toString() {
      return "TagKey[" + this.f_203867_.location() + " / " + this.f_203868_ + "]";
   }
}