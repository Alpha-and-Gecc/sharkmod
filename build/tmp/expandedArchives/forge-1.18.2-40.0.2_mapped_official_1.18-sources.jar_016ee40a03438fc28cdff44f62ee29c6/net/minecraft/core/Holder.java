package net.minecraft.core;

import com.mojang.datafixers.util.Either;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public interface Holder<T> {
   T m_203334_();

   boolean m_203633_();

   boolean m_203373_(ResourceLocation p_205713_);

   boolean m_203565_(ResourceKey<T> p_205712_);

   boolean m_203425_(Predicate<ResourceKey<T>> p_205711_);

   boolean m_203656_(TagKey<T> p_205705_);

   Stream<TagKey<T>> m_203616_();

   Either<ResourceKey<T>, T> m_203439_();

   Optional<ResourceKey<T>> m_203543_();

   Holder.Kind m_203376_();

   boolean m_203401_(Registry<T> p_205708_);

   static <T> Holder<T> m_205709_(T p_205710_) {
      return new Holder.Direct<>(p_205710_);
   }

   static <T> Holder<T> m_205706_(Holder<? extends T> p_205707_) {
      return (Holder<T>)p_205707_;
   }

   public static record Direct<T>(T f_205714_) implements Holder<T> {
      public boolean m_203633_() {
         return true;
      }

      public boolean m_203373_(ResourceLocation p_205727_) {
         return false;
      }

      public boolean m_203565_(ResourceKey<T> p_205725_) {
         return false;
      }

      public boolean m_203656_(TagKey<T> p_205719_) {
         return false;
      }

      public boolean m_203425_(Predicate<ResourceKey<T>> p_205723_) {
         return false;
      }

      public Either<ResourceKey<T>, T> m_203439_() {
         return Either.right(this.f_205714_);
      }

      public Optional<ResourceKey<T>> m_203543_() {
         return Optional.empty();
      }

      public Holder.Kind m_203376_() {
         return Holder.Kind.DIRECT;
      }

      public String toString() {
         return "Direct{" + this.f_205714_ + "}";
      }

      public boolean m_203401_(Registry<T> p_205721_) {
         return true;
      }

      public Stream<TagKey<T>> m_203616_() {
         return Stream.of();
      }

      public T m_203334_() {
         return this.f_205714_;
      }
   }

   public static enum Kind {
      REFERENCE,
      DIRECT;
   }

   public static class Reference<T> implements Holder<T> {
      private final Registry<T> f_205748_;
      private Set<TagKey<T>> f_205749_ = Set.of();
      private final Holder.Reference.Type f_205750_;
      @Nullable
      private ResourceKey<T> f_205751_;
      @Nullable
      private T f_205752_;

      private Reference(Holder.Reference.Type p_205754_, Registry<T> p_205755_, @Nullable ResourceKey<T> p_205756_, @Nullable T p_205757_) {
         this.f_205748_ = p_205755_;
         this.f_205750_ = p_205754_;
         this.f_205751_ = p_205756_;
         this.f_205752_ = p_205757_;
      }

      public static <T> Holder.Reference<T> m_205766_(Registry<T> p_205767_, ResourceKey<T> p_205768_) {
         return new Holder.Reference<>(Holder.Reference.Type.STAND_ALONE, p_205767_, p_205768_, (T)null);
      }

      /** @deprecated */
      @Deprecated
      public static <T> Holder.Reference<T> m_205763_(Registry<T> p_205764_, @Nullable T p_205765_) {
         return new Holder.Reference<>(Holder.Reference.Type.INTRUSIVE, p_205764_, (ResourceKey<T>)null, p_205765_);
      }

      public ResourceKey<T> m_205785_() {
         if (this.f_205751_ == null) {
            throw new IllegalStateException("Trying to access unbound value '" + this.f_205752_ + "' from registry " + this.f_205748_);
         } else {
            return this.f_205751_;
         }
      }

      public T m_203334_() {
         if (this.f_205752_ == null) {
            throw new IllegalStateException("Trying to access unbound value '" + this.f_205751_ + "' from registry " + this.f_205748_);
         } else {
            return this.f_205752_;
         }
      }

      public boolean m_203373_(ResourceLocation p_205779_) {
         return this.m_205785_().location().equals(p_205779_);
      }

      public boolean m_203565_(ResourceKey<T> p_205774_) {
         return this.m_205785_() == p_205774_;
      }

      public boolean m_203656_(TagKey<T> p_205760_) {
         return this.f_205749_.contains(p_205760_);
      }

      public boolean m_203425_(Predicate<ResourceKey<T>> p_205772_) {
         return p_205772_.test(this.m_205785_());
      }

      public boolean m_203401_(Registry<T> p_205762_) {
         return this.f_205748_ == p_205762_;
      }

      public Either<ResourceKey<T>, T> m_203439_() {
         return Either.left(this.m_205785_());
      }

      public Optional<ResourceKey<T>> m_203543_() {
         return Optional.of(this.m_205785_());
      }

      public Holder.Kind m_203376_() {
         return Holder.Kind.REFERENCE;
      }

      public boolean m_203633_() {
         return this.f_205751_ != null && this.f_205752_ != null;
      }

      public void m_205775_(ResourceKey<T> p_205776_, T p_205777_) {
         if (this.f_205751_ != null && p_205776_ != this.f_205751_) {
            throw new IllegalStateException("Can't change holder key: existing=" + this.f_205751_ + ", new=" + p_205776_);
         } else if (this.f_205750_ == Holder.Reference.Type.INTRUSIVE && this.f_205752_ != p_205777_) {
            throw new IllegalStateException("Can't change holder " + p_205776_ + " value: existing=" + this.f_205752_ + ", new=" + p_205777_);
         } else {
            this.f_205751_ = p_205776_;
            this.f_205752_ = p_205777_;
         }
      }

      public void m_205769_(Collection<TagKey<T>> p_205770_) {
         this.f_205749_ = Set.copyOf(p_205770_);
      }

      public Stream<TagKey<T>> m_203616_() {
         return this.f_205749_.stream();
      }

      public Type getType() {
         return this.f_205750_;
      }

      public String toString() {
         return "Reference{" + this.f_205751_ + "=" + this.f_205752_ + "}";
      }

      public static enum Type {
         STAND_ALONE,
         INTRUSIVE;
      }
   }
}
