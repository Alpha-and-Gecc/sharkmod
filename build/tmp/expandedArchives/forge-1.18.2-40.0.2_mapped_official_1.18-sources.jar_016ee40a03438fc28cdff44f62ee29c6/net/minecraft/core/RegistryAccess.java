package net.minecraft.core;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.UnboundedMapCodec;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.RegistryLoader;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.RegistryResourceAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.slf4j.Logger;

public interface RegistryAccess {
   Logger LOGGER = LogUtils.getLogger();
   Map<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>> REGISTRIES = Util.make(() -> {
      Builder<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>> builder = ImmutableMap.builder();
      put(builder, Registry.DIMENSION_TYPE_REGISTRY, DimensionType.DIRECT_CODEC, DimensionType.DIRECT_CODEC);
      put(builder, Registry.BIOME_REGISTRY, Biome.DIRECT_CODEC, Biome.NETWORK_CODEC);
      put(builder, Registry.CONFIGURED_CARVER_REGISTRY, ConfiguredWorldCarver.DIRECT_CODEC);
      put(builder, Registry.CONFIGURED_FEATURE_REGISTRY, ConfiguredFeature.DIRECT_CODEC);
      put(builder, Registry.PLACED_FEATURE_REGISTRY, PlacedFeature.DIRECT_CODEC);
      put(builder, Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, ConfiguredStructureFeature.DIRECT_CODEC);
      put(builder, Registry.f_211073_, StructureSet.f_210001_);
      put(builder, Registry.PROCESSOR_LIST_REGISTRY, StructureProcessorType.DIRECT_CODEC);
      put(builder, Registry.TEMPLATE_POOL_REGISTRY, StructureTemplatePool.f_210554_);
      put(builder, Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, NoiseGeneratorSettings.DIRECT_CODEC);
      put(builder, Registry.NOISE_REGISTRY, NormalNoise.NoiseParameters.DIRECT_CODEC);
      put(builder, Registry.f_211074_, DensityFunction.f_208216_);
      return builder.build();
   });
   Codec<RegistryAccess> f_206151_ = m_206205_();
   Supplier<RegistryAccess.Frozen> BUILTIN = Suppliers.memoize(() -> {
      return m_206197_().m_203557_();
   });

   <E> Optional<Registry<E>> ownedRegistry(ResourceKey<? extends Registry<? extends E>> p_175507_);

   default <E> Registry<E> m_206191_(ResourceKey<? extends Registry<? extends E>> p_206192_) {
      return this.ownedRegistry(p_206192_).orElseThrow(() -> {
         return new IllegalStateException("Missing registry: " + p_206192_);
      });
   }

   default <E> Optional<? extends Registry<E>> registry(ResourceKey<? extends Registry<? extends E>> p_123085_) {
      Optional<? extends Registry<E>> optional = this.ownedRegistry(p_123085_);
      return optional.isPresent() ? optional : (Optional<? extends Registry<E>>)Registry.REGISTRY.getOptional(p_123085_.location());
   }

   default <E> Registry<E> registryOrThrow(ResourceKey<? extends Registry<? extends E>> p_175516_) {
      return this.registry(p_175516_).orElseThrow(() -> {
         return new IllegalStateException("Missing registry: " + p_175516_);
      });
   }

   private static <E> void put(Builder<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>> p_123054_, ResourceKey<? extends Registry<E>> p_123055_, Codec<E> p_123056_) {
      p_123054_.put(p_123055_, new RegistryAccess.RegistryData<>(p_123055_, p_123056_, (Codec<E>)null));
   }

   private static <E> void put(Builder<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>> p_123058_, ResourceKey<? extends Registry<E>> p_123059_, Codec<E> p_123060_, Codec<E> p_123061_) {
      p_123058_.put(p_123059_, new RegistryAccess.RegistryData<>(p_123059_, p_123060_, p_123061_));
   }

   static Iterable<RegistryAccess.RegistryData<?>> knownRegistries() {
      return REGISTRIES.values();
   }

   Stream<RegistryAccess.RegistryEntry<?>> m_203610_();

   private static Stream<RegistryAccess.RegistryEntry<Object>> m_206202_() {
      return Registry.REGISTRY.m_203611_().map(RegistryAccess.RegistryEntry::m_206239_);
   }

   default Stream<RegistryAccess.RegistryEntry<?>> m_206193_() {
      return Stream.concat(this.m_203610_(), m_206202_());
   }

   default Stream<RegistryAccess.RegistryEntry<?>> m_206196_() {
      return Stream.concat(this.m_206206_(), m_206202_());
   }

   private static <E> Codec<RegistryAccess> m_206205_() {
      Codec<ResourceKey<? extends Registry<E>>> codec = ResourceLocation.CODEC.xmap(ResourceKey::createRegistryKey, ResourceKey::location);
      Codec<Registry<E>> codec1 = codec.partialDispatch("type", (p_206188_) -> {
         return DataResult.success(p_206188_.key());
      }, (p_206214_) -> {
         return m_206203_(p_206214_).map((p_206183_) -> {
            return RegistryCodecs.m_206291_(p_206214_, Lifecycle.experimental(), p_206183_);
         });
      });
      UnboundedMapCodec<? extends ResourceKey<? extends Registry<?>>, ? extends Registry<?>> unboundedmapcodec = Codec.unboundedMap(codec, codec1);
      return m_206163_(unboundedmapcodec);
   }

   private static <K extends ResourceKey<? extends Registry<?>>, V extends Registry<?>> Codec<RegistryAccess> m_206163_(UnboundedMapCodec<K, V> p_206164_) {
      return p_206164_.xmap(RegistryAccess.ImmutableRegistryAccess::new, (p_206180_) -> {
         return p_206180_.m_206206_().collect(ImmutableMap.toImmutableMap((p_206195_) -> {
            return (K)p_206195_.f_206233_();
         }, (p_206190_) -> {
            return (V)p_206190_.f_206234_();
         }));
      });
   }

   private Stream<RegistryAccess.RegistryEntry<?>> m_206206_() {
      return this.m_203610_().filter((p_206170_) -> {
         return REGISTRIES.get(p_206170_.f_206233_).sendToClient();
      });
   }

   private static <E> DataResult<? extends Codec<E>> m_206203_(ResourceKey<? extends Registry<E>> p_206204_) {
      return Optional.ofNullable(REGISTRIES.get(p_206204_)).map((p_206168_) -> {
         return (Codec<E>)p_206168_.networkCodec();
      }).map(DataResult::success).orElseGet(() -> {
         return DataResult.error("Unknown or not serializable registry: " + p_206204_);
      });
   }

   private static Map<ResourceKey<? extends Registry<?>>, ? extends WritableRegistry<?>> m_206209_() {
      return REGISTRIES.keySet().stream().collect(Collectors.toMap(Function.identity(), RegistryAccess::m_206200_));
   }

   private static RegistryAccess.Writable m_206212_() {
      return new RegistryAccess.WritableRegistryAccess(m_206209_());
   }

   static RegistryAccess.Frozen m_206165_(final Registry<? extends Registry<?>> p_206166_) {
      return new RegistryAccess.Frozen() {
         public <T> Optional<Registry<T>> ownedRegistry(ResourceKey<? extends Registry<? extends T>> p_206220_) {
            Registry<Registry<T>> registry = (Registry<Registry<T>>)p_206166_;
            return registry.getOptional((ResourceKey<Registry<T>>)p_206220_);
         }

         public Stream<RegistryAccess.RegistryEntry<?>> m_203610_() {
            return p_206166_.entrySet().stream().map(RegistryAccess.RegistryEntry::m_206241_);
         }
      };
   }

   static RegistryAccess.Writable m_206197_() {
      RegistryAccess.Writable registryaccess$writable = m_206212_();
      RegistryResourceAccess.InMemoryStorage registryresourceaccess$inmemorystorage = new RegistryResourceAccess.InMemoryStorage();

      for(Entry<ResourceKey<? extends Registry<?>>, RegistryAccess.RegistryData<?>> entry : REGISTRIES.entrySet()) {
         if (!entry.getKey().equals(Registry.DIMENSION_TYPE_REGISTRY)) {
            m_211081_(registryresourceaccess$inmemorystorage, entry.getValue());
         }
      }

      RegistryOps.m_206817_(JsonOps.INSTANCE, registryaccess$writable, registryresourceaccess$inmemorystorage);
      return DimensionType.m_204488_(registryaccess$writable);
   }

   private static <E> void m_211081_(RegistryResourceAccess.InMemoryStorage p_211082_, RegistryAccess.RegistryData<E> p_211083_) {
      ResourceKey<? extends Registry<E>> resourcekey = p_211083_.key();
      Registry<E> registry = BuiltinRegistries.f_206379_.registryOrThrow(resourcekey);
      if (!resourcekey.equals(Registry.DIMENSION_TYPE_REGISTRY))
         registry = ((Registry<Registry<E>>)BuiltinRegistries.REGISTRY).get((ResourceKey<Registry<E>>)resourcekey);

      for(Entry<ResourceKey<E>, E> entry : registry.entrySet()) {
         ResourceKey<E> resourcekey1 = entry.getKey();
         E e = entry.getValue();
         p_211082_.m_206836_(BuiltinRegistries.f_206379_, resourcekey1, p_211083_.codec(), registry.getId(e), e, registry.lifecycle(e));
      }

   }

   static void m_206171_(RegistryAccess.Writable p_206172_, DynamicOps<JsonElement> p_206173_, RegistryLoader p_206174_) {
      RegistryLoader.Bound registryloader$bound = p_206174_.m_206757_(p_206172_);

      for(RegistryAccess.RegistryData<?> registrydata : REGISTRIES.values()) {
         m_206159_(p_206173_, registryloader$bound, registrydata);
      }

   }

   private static <E> void m_206159_(DynamicOps<JsonElement> p_206160_, RegistryLoader.Bound p_206161_, RegistryAccess.RegistryData<E> p_206162_) {
      DataResult<? extends Registry<E>> dataresult = p_206161_.m_206789_(p_206162_.key(), p_206162_.codec(), p_206160_);
      dataresult.error().ifPresent((p_206153_) -> {
         throw new JsonParseException("Error loading registry data: " + p_206153_.message());
      });
   }

   static RegistryAccess m_206154_(Dynamic<?> p_206155_) {
      return new RegistryAccess.ImmutableRegistryAccess(REGISTRIES.keySet().stream().collect(Collectors.toMap(Function.identity(), (p_206158_) -> {
         return m_206184_(p_206158_, p_206155_);
      })));
   }

   static <E> Registry<E> m_206184_(ResourceKey<? extends Registry<? extends E>> p_206185_, Dynamic<?> p_206186_) {
      return RegistryOps.m_206832_(p_206185_).codec().parse(p_206186_).resultOrPartial(Util.prefix(p_206185_ + " registry: ", LOGGER::error)).orElseThrow(() -> {
         return new IllegalStateException("Failed to get " + p_206185_ + " registry");
      });
   }

   static <E> WritableRegistry<?> m_206200_(ResourceKey<? extends Registry<?>> p_206201_) {
      return new MappedRegistry<>((ResourceKey<? extends Registry<E>>)p_206201_, Lifecycle.stable(), (Function<E, Holder.Reference<E>>)null);
   }

   default RegistryAccess.Frozen m_203557_() {
      return new RegistryAccess.ImmutableRegistryAccess(this.m_203610_().map(RegistryAccess.RegistryEntry::m_206247_));
   }

   default Lifecycle m_211816_() {
      return this.m_203610_().map((p_211815_) -> {
         return p_211815_.f_206234_.elementsLifecycle();
      }).reduce(Lifecycle.stable(), Lifecycle::add);
   }

   public interface Frozen extends RegistryAccess {
      default RegistryAccess.Frozen m_203557_() {
         return this;
      }
   }

   public static final class ImmutableRegistryAccess implements RegistryAccess.Frozen {
      private final Map<? extends ResourceKey<? extends Registry<?>>, ? extends Registry<?>> f_206223_;

      public ImmutableRegistryAccess(Map<? extends ResourceKey<? extends Registry<?>>, ? extends Registry<?>> p_206225_) {
         this.f_206223_ = Map.copyOf(p_206225_);
      }

      ImmutableRegistryAccess(Stream<RegistryAccess.RegistryEntry<?>> p_206227_) {
         this.f_206223_ = p_206227_.collect(ImmutableMap.toImmutableMap(RegistryAccess.RegistryEntry::f_206233_, RegistryAccess.RegistryEntry::f_206234_));
      }

      public <E> Optional<Registry<E>> ownedRegistry(ResourceKey<? extends Registry<? extends E>> p_206229_) {
         return Optional.ofNullable(this.f_206223_.get(p_206229_)).map((p_206232_) -> {
            return (Registry<E>)p_206232_;
         });
      }

      public Stream<RegistryAccess.RegistryEntry<?>> m_203610_() {
         return this.f_206223_.entrySet().stream().map(RegistryAccess.RegistryEntry::m_206241_);
      }
   }

   public static record RegistryData<E>(ResourceKey<? extends Registry<E>> key, Codec<E> codec, @Nullable Codec<E> networkCodec) {
      public boolean sendToClient() {
         return this.networkCodec != null;
      }
   }

   public static record RegistryEntry<T>(ResourceKey<? extends Registry<T>> f_206233_, Registry<T> f_206234_) {
      private static <T, R extends Registry<? extends T>> RegistryAccess.RegistryEntry<T> m_206241_(Entry<? extends ResourceKey<? extends Registry<?>>, R> p_206242_) {
         return m_206243_(p_206242_.getKey(), p_206242_.getValue());
      }

      private static <T> RegistryAccess.RegistryEntry<T> m_206239_(Holder.Reference<? extends Registry<? extends T>> p_206240_) {
         return m_206243_(p_206240_.m_205785_(), p_206240_.m_203334_());
      }

      private static <T> RegistryAccess.RegistryEntry<T> m_206243_(ResourceKey<? extends Registry<?>> p_206244_, Registry<?> p_206245_) {
         return new RegistryAccess.RegistryEntry<>((ResourceKey<? extends Registry<T>>)p_206244_, (Registry<T>)p_206245_);
      }

      private RegistryAccess.RegistryEntry<T> m_206247_() {
         return new RegistryAccess.RegistryEntry<>(this.f_206233_, this.f_206234_.m_203521_());
      }
   }

   public interface Writable extends RegistryAccess {
      <E> Optional<WritableRegistry<E>> m_203275_(ResourceKey<? extends Registry<? extends E>> p_206252_);

      default <E> WritableRegistry<E> m_206253_(ResourceKey<? extends Registry<? extends E>> p_206254_) {
         return this.<E>m_203275_(p_206254_).orElseThrow(() -> {
            return new IllegalStateException("Missing registry: " + p_206254_);
         });
      }
   }

   public static final class WritableRegistryAccess implements RegistryAccess.Writable {
      private final Map<? extends ResourceKey<? extends Registry<?>>, ? extends WritableRegistry<?>> f_206257_;

      WritableRegistryAccess(Map<? extends ResourceKey<? extends Registry<?>>, ? extends WritableRegistry<?>> p_206259_) {
         this.f_206257_ = p_206259_;
      }

      public <E> Optional<Registry<E>> ownedRegistry(ResourceKey<? extends Registry<? extends E>> p_206263_) {
         return Optional.ofNullable(this.f_206257_.get(p_206263_)).map((p_206266_) -> {
            return (Registry<E>)p_206266_;
         });
      }

      public <E> Optional<WritableRegistry<E>> m_203275_(ResourceKey<? extends Registry<? extends E>> p_206268_) {
         return Optional.ofNullable(this.f_206257_.get(p_206268_)).map((p_206261_) -> {
            return (WritableRegistry<E>)p_206261_;
         });
      }

      public Stream<RegistryAccess.RegistryEntry<?>> m_203610_() {
         return this.f_206257_.entrySet().stream().map(RegistryAccess.RegistryEntry::m_206241_);
      }
   }
}
