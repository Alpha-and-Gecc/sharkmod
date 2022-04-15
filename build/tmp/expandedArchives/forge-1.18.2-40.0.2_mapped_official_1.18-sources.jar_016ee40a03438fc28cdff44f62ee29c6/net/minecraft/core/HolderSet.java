package net.minecraft.core;

import com.mojang.datafixers.util.Either;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraft.Util;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HolderSet<T> extends Iterable<Holder<T>> {
   Stream<Holder<T>> m_203614_();

   int m_203632_();

   Either<TagKey<T>, List<Holder<T>>> m_203440_();

   Optional<Holder<T>> m_203450_(Random p_205802_);

   Holder<T> m_203662_(int p_205798_);

   boolean m_203333_(Holder<T> p_205799_);

   boolean m_207277_(Registry<T> p_211041_);

   @SafeVarargs
   static <T> HolderSet.Direct<T> m_205809_(Holder<T>... p_205810_) {
      return new HolderSet.Direct<>(List.of(p_205810_));
   }

   static <T> HolderSet.Direct<T> m_205800_(List<? extends Holder<T>> p_205801_) {
      return new HolderSet.Direct<>(List.copyOf(p_205801_));
   }

   @SafeVarargs
   static <E, T> HolderSet.Direct<T> m_205806_(Function<E, Holder<T>> p_205807_, E... p_205808_) {
      return m_205800_(Stream.of(p_205808_).map(p_205807_).toList());
   }

   static <E, T> HolderSet.Direct<T> m_205803_(Function<E, Holder<T>> p_205804_, List<E> p_205805_) {
      return m_205800_(p_205805_.stream().map(p_205804_).toList());
   }

   public static class Direct<T> extends HolderSet.ListBacked<T> {
      private final List<Holder<T>> f_205811_;
      @Nullable
      private Set<Holder<T>> f_205812_;

      Direct(List<Holder<T>> p_205814_) {
         this.f_205811_ = p_205814_;
      }

      protected List<Holder<T>> m_203661_() {
         return this.f_205811_;
      }

      public Either<TagKey<T>, List<Holder<T>>> m_203440_() {
         return Either.right(this.f_205811_);
      }

      public boolean m_203333_(Holder<T> p_205816_) {
         if (this.f_205812_ == null) {
            this.f_205812_ = Set.copyOf(this.f_205811_);
         }

         return this.f_205812_.contains(p_205816_);
      }

      public String toString() {
         return "DirectSet[" + this.f_205811_ + "]";
      }
   }

   public abstract static class ListBacked<T> implements HolderSet<T> {
      protected abstract List<Holder<T>> m_203661_();

      public int m_203632_() {
         return this.m_203661_().size();
      }

      public Spliterator<Holder<T>> spliterator() {
         return this.m_203661_().spliterator();
      }

      @NotNull
      public Iterator<Holder<T>> iterator() {
         return this.m_203661_().iterator();
      }

      public Stream<Holder<T>> m_203614_() {
         return this.m_203661_().stream();
      }

      public Optional<Holder<T>> m_203450_(Random p_205825_) {
         return Util.m_203747_(this.m_203661_(), p_205825_);
      }

      public Holder<T> m_203662_(int p_205823_) {
         return this.m_203661_().get(p_205823_);
      }

      public boolean m_207277_(Registry<T> p_211043_) {
         return true;
      }
   }

   public static class Named<T> extends HolderSet.ListBacked<T> {
      private final Registry<T> f_211044_;
      private final TagKey<T> f_205829_;
      private List<Holder<T>> f_205830_ = List.of();

      public Named(Registry<T> p_211046_, TagKey<T> p_211047_) {
         this.f_211044_ = p_211046_;
         this.f_205829_ = p_211047_;
      }

      public void m_205835_(List<Holder<T>> p_205836_) {
         this.f_205830_ = List.copyOf(p_205836_);
      }

      public TagKey<T> m_205839_() {
         return this.f_205829_;
      }

      protected List<Holder<T>> m_203661_() {
         return this.f_205830_;
      }

      public Either<TagKey<T>, List<Holder<T>>> m_203440_() {
         return Either.left(this.f_205829_);
      }

      public boolean m_203333_(Holder<T> p_205834_) {
         return p_205834_.m_203656_(this.f_205829_);
      }

      public String toString() {
         return "NamedSet(" + this.f_205829_ + ")[" + this.f_205830_ + "]";
      }

      public boolean m_207277_(Registry<T> p_211049_) {
         return this.f_211044_ == p_211049_;
      }
   }
}