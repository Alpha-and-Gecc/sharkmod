package net.minecraft.resources;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;

public final class RegistryFixedCodec<E> implements Codec<Holder<E>> {
   private final ResourceKey<? extends Registry<E>> f_206721_;

   public static <E> RegistryFixedCodec<E> m_206740_(ResourceKey<? extends Registry<E>> p_206741_) {
      return new RegistryFixedCodec<>(p_206741_);
   }

   private RegistryFixedCodec(ResourceKey<? extends Registry<E>> p_206723_) {
      this.f_206721_ = p_206723_;
   }

   public <T> DataResult<T> encode(Holder<E> p_206729_, DynamicOps<T> p_206730_, T p_206731_) {
      if (p_206730_ instanceof RegistryOps) {
         RegistryOps<?> registryops = (RegistryOps)p_206730_;
         Optional<? extends Registry<E>> optional = registryops.m_206826_(this.f_206721_);
         if (optional.isPresent()) {
            if (!p_206729_.m_203401_(optional.get())) {
               return DataResult.error("Element " + p_206729_ + " is not valid in current registry set");
            }

            return p_206729_.m_203439_().map((p_206727_) -> {
               return ResourceLocation.CODEC.encode(p_206727_.location(), p_206730_, p_206731_);
            }, (p_206733_) -> {
               return DataResult.error("Elements from registry " + this.f_206721_ + " can't be serialized to a value");
            });
         }
      }

      return DataResult.error("Can't access registry " + this.f_206721_);
   }

   public <T> DataResult<Pair<Holder<E>, T>> decode(DynamicOps<T> p_206743_, T p_206744_) {
      if (p_206743_ instanceof RegistryOps) {
         RegistryOps<?> registryops = (RegistryOps)p_206743_;
         Optional<? extends Registry<E>> optional = registryops.m_206826_(this.f_206721_);
         if (optional.isPresent()) {
            return ResourceLocation.CODEC.decode(p_206743_, p_206744_).map((p_206736_) -> {
               return p_206736_.mapFirst((p_206739_) -> {
                  return optional.get().m_203538_(ResourceKey.create(this.f_206721_, p_206739_));
               });
            });
         }
      }

      return DataResult.error("Can't access registry " + this.f_206721_);
   }

   public String toString() {
      return "RegistryFixedCodec[" + this.f_206721_ + "]";
   }
}