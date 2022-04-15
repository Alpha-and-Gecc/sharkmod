package net.minecraft.resources;

import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import java.util.Optional;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.ExtraCodecs;

public class RegistryOps<T> extends DelegatingOps<T> {
   private final Optional<RegistryLoader.Bound> f_206805_;
   private final RegistryAccess f_206806_;
   private final DynamicOps<JsonElement> f_206807_;

   public static <T> RegistryOps<T> m_206821_(DynamicOps<T> p_206822_, RegistryAccess p_206823_) {
      return new RegistryOps<>(p_206822_, p_206823_, Optional.empty());
   }

   public static <T> RegistryOps<T> m_206813_(DynamicOps<T> p_206814_, RegistryAccess.Writable p_206815_, ResourceManager p_206816_) {
      return m_206817_(p_206814_, p_206815_, RegistryResourceAccess.forResourceManager(p_206816_));
   }

   public static <T> RegistryOps<T> m_206817_(DynamicOps<T> p_206818_, RegistryAccess.Writable p_206819_, RegistryResourceAccess p_206820_) {
      RegistryLoader registryloader = new RegistryLoader(p_206820_);
      RegistryOps<T> registryops = new RegistryOps<>(p_206818_, p_206819_, Optional.of(registryloader.m_206757_(p_206819_)));
      RegistryAccess.m_206171_(p_206819_, registryops.m_206831_(), registryloader);
      return registryops;
   }

   private RegistryOps(DynamicOps<T> p_206809_, RegistryAccess p_206810_, Optional<RegistryLoader.Bound> p_206811_) {
      super(p_206809_);
      this.f_206805_ = p_206811_;
      this.f_206806_ = p_206810_;
      this.f_206807_ = p_206809_ == JsonOps.INSTANCE ? (DynamicOps<JsonElement>) this : new RegistryOps<>(JsonOps.INSTANCE, p_206810_, p_206811_);
   }

   public <E> Optional<? extends Registry<E>> m_206826_(ResourceKey<? extends Registry<? extends E>> p_206827_) {
      return this.f_206806_.registry(p_206827_);
   }

   public Optional<RegistryLoader.Bound> m_206812_() {
      return this.f_206805_;
   }

   public DynamicOps<JsonElement> m_206831_() {
      return this.f_206807_;
   }

   public static <E> MapCodec<Registry<E>> m_206832_(ResourceKey<? extends Registry<? extends E>> p_206833_) {
      return ExtraCodecs.m_203976_((p_206830_) -> {
         if (p_206830_ instanceof RegistryOps) {
            RegistryOps<?> registryops = (RegistryOps)p_206830_;
            return registryops.m_206826_(p_206833_).map((p_206825_) -> {
               return DataResult.<Registry<E>>success(p_206825_, p_206825_.elementsLifecycle());
            }).orElseGet(() -> {
               return DataResult.error("Unknown registry: " + p_206833_);
            });
         } else {
            return DataResult.error("Not a registry ops");
         }
      });
   }
}