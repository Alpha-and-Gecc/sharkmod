package net.minecraft.core;

import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;

public class MappedRegistry<T> extends WritableRegistry<T> {
   private static final Logger f_211050_ = LogUtils.getLogger();
   private final ObjectList<Holder.Reference<T>> byId = new ObjectArrayList<>(256);
   private final Object2IntMap<T> toId = Util.make(new Object2IntOpenCustomHashMap<>(Util.identityStrategy()), (p_194539_) -> {
      p_194539_.defaultReturnValue(-1);
   });
   private final Map<ResourceLocation, Holder.Reference<T>> f_205841_ = new HashMap<>();
   private final Map<ResourceKey<T>, Holder.Reference<T>> f_205842_ = new HashMap<>();
   private final Map<T, Holder.Reference<T>> f_205843_ = new IdentityHashMap<>();
   private final Map<T, Lifecycle> lifecycles = new IdentityHashMap<>();
   private Lifecycle elementsLifecycle;
   private volatile Map<TagKey<T>, HolderSet.Named<T>> f_205844_ = new IdentityHashMap<>();
   private boolean f_205845_;
   @Nullable
   private final Function<T, Holder.Reference<T>> f_205846_;
   @Nullable
   private Map<T, Holder.Reference<T>> f_205847_;
   @Nullable
   private List<Holder.Reference<T>> f_211051_;
   private int nextId;

   public MappedRegistry(ResourceKey<? extends Registry<T>> p_205849_, Lifecycle p_205850_, @Nullable Function<T, Holder.Reference<T>> p_205851_) {
      super(p_205849_, p_205850_);
      this.elementsLifecycle = p_205850_;
      this.f_205846_ = p_205851_;
      if (p_205851_ != null) {
         this.f_205847_ = new IdentityHashMap<>();
      }

   }

   private List<Holder.Reference<T>> m_211053_() {
      if (this.f_211051_ == null) {
         this.f_211051_ = this.byId.stream().filter(Objects::nonNull).toList();
      }

      return this.f_211051_;
   }

   private void m_205921_(ResourceKey<T> p_205922_) {
      if (this.f_205845_) {
         throw new IllegalStateException("Registry is already frozen (trying to add key " + p_205922_ + ")");
      }
   }

   public Holder<T> m_203704_(int p_205853_, ResourceKey<T> p_205854_, T p_205855_, Lifecycle p_205856_) {
      return this.m_205857_(p_205853_, p_205854_, p_205855_, p_205856_, true);
   }

   private Holder<T> m_205857_(int p_205858_, ResourceKey<T> p_205859_, T p_205860_, Lifecycle p_205861_, boolean p_205862_) {
      this.m_205921_(p_205859_);
      Validate.notNull(p_205859_);
      Validate.notNull(p_205860_);
      this.byId.size(Math.max(this.byId.size(), p_205858_ + 1));
      this.toId.put(p_205860_, p_205858_);
      this.f_211051_ = null;
      if (p_205862_ && this.f_205842_.containsKey(p_205859_)) {
         Util.logAndPauseIfInIde("Adding duplicate key '" + p_205859_ + "' to registry");
      }

      if (this.f_205843_.containsKey(p_205860_)) {
         Util.logAndPauseIfInIde("Adding duplicate value '" + p_205860_ + "' to registry");
      }

      this.lifecycles.put(p_205860_, p_205861_);
      this.elementsLifecycle = this.elementsLifecycle.add(p_205861_);
      if (this.nextId <= p_205858_) {
         this.nextId = p_205858_ + 1;
      }

      Holder.Reference<T> reference;
      if (this.f_205846_ != null) {
         reference = this.f_205846_.apply(p_205860_);
         Holder.Reference<T> reference1 = this.f_205842_.put(p_205859_, reference);
         if (reference1 != null && reference1 != reference) {
            throw new IllegalStateException("Invalid holder present for key " + p_205859_);
         }
      } else {
         reference = this.f_205842_.computeIfAbsent(p_205859_, (p_205927_) -> {
            return Holder.Reference.m_205766_(this, p_205927_);
         });
      }

      this.f_205841_.put(p_205859_.location(), reference);
      this.f_205843_.put(p_205860_, reference);
      reference.m_205775_(p_205859_, p_205860_);
      this.byId.set(p_205858_, reference);
      return reference;
   }

   public Holder<T> m_203505_(ResourceKey<T> p_205891_, T p_205892_, Lifecycle p_205893_) {
      return this.m_203704_(this.nextId, p_205891_, p_205892_, p_205893_);
   }

   public Holder<T> m_203384_(OptionalInt p_205884_, ResourceKey<T> p_205885_, T p_205886_, Lifecycle p_205887_) {
      this.m_205921_(p_205885_);
      Validate.notNull(p_205885_);
      Validate.notNull(p_205886_);
      Holder<T> holder = this.f_205842_.get(p_205885_);
      T t = (T)(holder != null && holder.m_203633_() ? holder.m_203334_() : null);
      int i;
      if (t == null) {
         i = p_205884_.orElse(this.nextId);
      } else {
         i = this.toId.getInt(t);
         if (p_205884_.isPresent() && p_205884_.getAsInt() != i) {
            throw new IllegalStateException("ID mismatch");
         }

         this.lifecycles.remove(t);
         this.toId.removeInt(t);
         this.f_205843_.remove(t);
      }

      return this.m_205857_(i, p_205885_, p_205886_, p_205887_, false);
   }

   @Nullable
   public ResourceLocation getKey(T p_122746_) {
      Holder.Reference<T> reference = this.f_205843_.get(p_122746_);
      return reference != null ? reference.m_205785_().location() : null;
   }

   public Optional<ResourceKey<T>> getResourceKey(T p_122755_) {
      return Optional.ofNullable(this.f_205843_.get(p_122755_)).map(Holder.Reference::m_205785_);
   }

   public int getId(@Nullable T p_122706_) {
      return this.toId.getInt(p_122706_);
   }

   @Nullable
   public T get(@Nullable ResourceKey<T> p_122714_) {
      return m_205865_(this.f_205842_.get(p_122714_));
   }

   @Nullable
   public T byId(int p_122684_) {
      return (T)(p_122684_ >= 0 && p_122684_ < this.byId.size() ? m_205865_(this.byId.get(p_122684_)) : null);
   }

   public Optional<Holder<T>> m_203300_(int p_205907_) {
      return p_205907_ >= 0 && p_205907_ < this.byId.size() ? Optional.ofNullable(this.byId.get(p_205907_)) : Optional.empty();
   }

   public Optional<Holder<T>> m_203636_(ResourceKey<T> p_205905_) {
      return Optional.ofNullable(this.f_205842_.get(p_205905_));
   }

   public Holder<T> m_203538_(ResourceKey<T> p_205913_) {
      return this.f_205842_.computeIfAbsent(p_205913_, (p_205924_) -> {
         if (this.f_205846_ != null) {
            throw new IllegalStateException("This registry can't create new holders without value");
         } else {
            this.m_205921_(p_205924_);
            return Holder.Reference.m_205766_(this, p_205924_);
         }
      });
   }

   public int size() {
      return this.f_205842_.size();
   }

   public Lifecycle lifecycle(T p_122764_) {
      return this.lifecycles.get(p_122764_);
   }

   public Lifecycle elementsLifecycle() {
      return this.elementsLifecycle;
   }

   public Iterator<T> iterator() {
      return Iterators.transform(this.m_211053_().iterator(), Holder::m_203334_);
   }

   @Nullable
   public T get(@Nullable ResourceLocation p_122739_) {
      Holder.Reference<T> reference = this.f_205841_.get(p_122739_);
      return m_205865_(reference);
   }

   @Nullable
   private static <T> T m_205865_(@Nullable Holder.Reference<T> p_205866_) {
      return (T)(p_205866_ != null ? p_205866_.m_203334_() : null);
   }

   public Set<ResourceLocation> keySet() {
      return Collections.unmodifiableSet(this.f_205841_.keySet());
   }

   public Set<Entry<ResourceKey<T>, T>> entrySet() {
      return Collections.unmodifiableSet(Maps.transformValues(this.f_205842_, Holder::m_203334_).entrySet());
   }

   public Stream<Holder.Reference<T>> m_203611_() {
      return this.m_211053_().stream();
   }

   public boolean m_203658_(TagKey<T> p_205864_) {
      return this.f_205844_.containsKey(p_205864_);
   }

   public Stream<Pair<TagKey<T>, HolderSet.Named<T>>> m_203612_() {
      return this.f_205844_.entrySet().stream().map((p_211060_) -> {
         return Pair.of(p_211060_.getKey(), p_211060_.getValue());
      });
   }

   public HolderSet.Named<T> m_203561_(TagKey<T> p_205895_) {
      HolderSet.Named<T> named = this.f_205844_.get(p_205895_);
      if (named == null) {
         named = this.m_211067_(p_205895_);
         Map<TagKey<T>, HolderSet.Named<T>> map = new IdentityHashMap<>(this.f_205844_);
         map.put(p_205895_, named);
         this.f_205844_ = map;
      }

      return named;
   }

   private HolderSet.Named<T> m_211067_(TagKey<T> p_211068_) {
      return new HolderSet.Named<>(this, p_211068_);
   }

   public Stream<TagKey<T>> m_203613_() {
      return this.f_205844_.keySet().stream();
   }

   public boolean isEmpty() {
      return this.f_205842_.isEmpty();
   }

   public Optional<Holder<T>> m_203454_(Random p_205889_) {
      return Util.m_203747_(this.m_211053_(), p_205889_).map(Holder::m_205706_);
   }

   public boolean containsKey(ResourceLocation p_122761_) {
      return this.f_205841_.containsKey(p_122761_);
   }

   public boolean containsKey(ResourceKey<T> p_175392_) {
      return this.f_205842_.containsKey(p_175392_);
   }

   /** @Depreciated Forge: For internal use only. Use the Register events when registering values. */
   @Deprecated
   public void unfreeze() {
      this.f_205845_ = false;
      if (this.f_205846_ != null && this.f_205847_ == null)
         this.f_205847_ = new IdentityHashMap<>();
   }

   public Registry<T> m_203521_() {
      this.f_205845_ = true;
      List<ResourceLocation> list = this.f_205842_.entrySet().stream().filter((p_211055_) -> {
         return !p_211055_.getValue().m_203633_();
      }).map((p_211794_) -> {
         return p_211794_.getKey().location();
      }).sorted().toList();
      if (!list.isEmpty()) {
         throw new IllegalStateException("Unbound values in registry " + this.key() + ": " + list);
      } else {
         if (this.f_205847_ != null) {
            List<Holder.Reference<T>> list1 = this.f_205847_.values().stream().filter((p_211809_) -> {
               return !p_211809_.m_203633_();
            }).toList();
            if (!list1.isEmpty()) {
               throw new IllegalStateException("Some intrusive holders were not added to registry: " + list1);
            }

            this.f_205847_ = null;
         }

         return this;
      }
   }

   public Holder.Reference<T> m_203693_(T p_205915_) {
      if (this.f_205846_ == null) {
         throw new IllegalStateException("This registry can't create intrusive holders");
      } else if (!this.f_205845_ && this.f_205847_ != null) {
         return this.f_205847_.computeIfAbsent(p_205915_, (p_211813_) -> {
            return Holder.Reference.m_205763_(this, p_211813_);
         });
      } else {
         throw new IllegalStateException("Registry is already frozen");
      }
   }

   public Optional<HolderSet.Named<T>> m_203431_(TagKey<T> p_205909_) {
      return Optional.ofNullable(this.f_205844_.get(p_205909_));
   }

   public void m_203652_(Map<TagKey<T>, List<Holder<T>>> p_205875_) {
      Map<Holder.Reference<T>, List<TagKey<T>>> map = new IdentityHashMap<>();
      this.f_205842_.values().forEach((p_211801_) -> {
         map.put(p_211801_, new ArrayList<>());
      });
      p_205875_.forEach((p_211806_, p_211807_) -> {
         for(Holder<T> holder : p_211807_) {
            if (!holder.m_203401_(this)) {
               throw new IllegalStateException("Can't create named set " + p_211806_ + " containing value " + holder + " from outside registry " + this);
            }

            if (!(holder instanceof Holder.Reference)) {
               throw new IllegalStateException("Found direct holder " + holder + " value in tag " + p_211806_);
            }

            Holder.Reference<T> reference = (Holder.Reference)holder;
            map.get(reference).add(p_211806_);
         }

      });
      Set<TagKey<T>> set = Sets.difference(this.f_205844_.keySet(), p_205875_.keySet());
      if (!set.isEmpty()) {
         f_211050_.warn("Not all defined tags for registry {} are present in data pack: {}", this.key(), set.stream().map((p_211811_) -> {
            return p_211811_.f_203868_().toString();
         }).sorted().collect(Collectors.joining(", ")));
      }

      Map<TagKey<T>, HolderSet.Named<T>> map1 = new IdentityHashMap<>(this.f_205844_);
      p_205875_.forEach((p_211797_, p_211798_) -> {
         map1.computeIfAbsent(p_211797_, this::m_211067_).m_205835_(p_211798_);
      });
      map.forEach(Holder.Reference::m_205769_);
      this.f_205844_ = map1;
   }

   public void m_203635_() {
      this.f_205844_.values().forEach((p_211792_) -> {
         p_211792_.m_205835_(List.of());
      });
      this.f_205842_.values().forEach((p_211803_) -> {
         p_211803_.m_205769_(Set.of());
      });
   }
}
