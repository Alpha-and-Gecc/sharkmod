package net.minecraft.tags;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class TagNetworkSerialization {
   public static Map<ResourceKey<? extends Registry<?>>, TagNetworkSerialization.NetworkPayload> m_203950_(RegistryAccess p_203951_) {
      return p_203951_.m_206196_().map((p_203949_) -> {
         return Pair.of(p_203949_.f_206233_(), m_203942_(p_203949_.f_206234_()));
      }).filter((p_203941_) -> {
         return !p_203941_.getSecond().m_203966_();
      }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
   }

   private static <T> TagNetworkSerialization.NetworkPayload m_203942_(Registry<T> p_203943_) {
      Map<ResourceLocation, IntList> map = new HashMap<>();
      p_203943_.m_203612_().forEach((p_203947_) -> {
         HolderSet<T> holderset = p_203947_.getSecond();
         IntList intlist = new IntArrayList(holderset.m_203632_());

         for(Holder<T> holder : holderset) {
            if (holder.m_203376_() != Holder.Kind.REFERENCE) {
               throw new IllegalStateException("Can't serialize unregistered value " + holder);
            }

            intlist.add(p_203943_.getId(holder.m_203334_()));
         }

         map.put(p_203947_.getFirst().f_203868_(), intlist);
      });
      return new TagNetworkSerialization.NetworkPayload(map);
   }

   public static <T> void m_203952_(ResourceKey<? extends Registry<T>> p_203953_, Registry<T> p_203954_, TagNetworkSerialization.NetworkPayload p_203955_, TagNetworkSerialization.TagOutput<T> p_203956_) {
      p_203955_.f_203963_.forEach((p_203961_, p_203962_) -> {
         TagKey<T> tagkey = TagKey.m_203882_(p_203953_, p_203961_);
         List<Holder<T>> list = p_203962_.intStream().mapToObj(p_203954_::m_203300_).flatMap(Optional::stream).toList();
         p_203956_.m_203971_(tagkey, list);
      });
   }

   public static final class NetworkPayload {
      final Map<ResourceLocation, IntList> f_203963_;

      NetworkPayload(Map<ResourceLocation, IntList> p_203965_) {
         this.f_203963_ = p_203965_;
      }

      public void m_203967_(FriendlyByteBuf p_203968_) {
         p_203968_.writeMap(this.f_203963_, FriendlyByteBuf::writeResourceLocation, FriendlyByteBuf::writeIntIdList);
      }

      public static TagNetworkSerialization.NetworkPayload m_203969_(FriendlyByteBuf p_203970_) {
         return new TagNetworkSerialization.NetworkPayload(p_203970_.readMap(FriendlyByteBuf::readResourceLocation, FriendlyByteBuf::readIntIdList));
      }

      public boolean m_203966_() {
         return this.f_203963_.isEmpty();
      }
   }

   @FunctionalInterface
   public interface TagOutput<T> {
      void m_203971_(TagKey<T> p_203972_, List<Holder<T>> p_203973_);
   }
}