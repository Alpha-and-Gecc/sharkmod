package net.minecraft.tags;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

public class TagManager implements PreparableReloadListener {
   private static final Map<ResourceKey<? extends Registry<?>>, String> f_203902_ = Map.of(Registry.BLOCK_REGISTRY, "tags/blocks", Registry.ENTITY_TYPE_REGISTRY, "tags/entity_types", Registry.FLUID_REGISTRY, "tags/fluids", Registry.GAME_EVENT_REGISTRY, "tags/game_events", Registry.ITEM_REGISTRY, "tags/items");
   private final RegistryAccess registryAccess;
   protected java.util.Map<ResourceLocation, TagLoader<?>> customTagTypeReaders = net.minecraftforge.common.ForgeTagHandler.createCustomTagTypeReaders();
   private List<TagManager.LoadResult<?>> f_203903_ = List.of();

   public TagManager(RegistryAccess p_144572_) {
      this.registryAccess = p_144572_;
   }

   public List<TagManager.LoadResult<?>> m_203904_() {
      return this.f_203903_;
   }

   public static String m_203918_(ResourceKey<? extends Registry<?>> p_203919_) {
      String s = f_203902_.get(p_203919_);
      return s != null ? s : "tags/" + p_203919_.location().getPath();
   }

   public CompletableFuture<Void> reload(PreparableReloadListener.PreparationBarrier p_13482_, ResourceManager p_13483_, ProfilerFiller p_13484_, ProfilerFiller p_13485_, Executor p_13486_, Executor p_13487_) {
      List<? extends CompletableFuture<? extends TagManager.LoadResult<?>>> list = this.registryAccess.m_206193_().map((p_203927_) -> {
         return this.m_203907_(p_13483_, p_13486_, p_203927_);
      }).toList();
      return CompletableFuture.allOf(list.toArray((p_203906_) -> {
         return new CompletableFuture[p_203906_];
      })).thenCompose(p_13482_::wait).thenAcceptAsync((p_203917_) -> {
         this.f_203903_ = list.stream().map(CompletableFuture::join).collect(Collectors.toUnmodifiableList());
      }, p_13487_);
   }

   private <T> CompletableFuture<TagManager.LoadResult<T>> m_203907_(ResourceManager p_203908_, Executor p_203909_, RegistryAccess.RegistryEntry<T> p_203910_) {
      ResourceKey<? extends Registry<T>> resourcekey = p_203910_.f_206233_();
      Registry<T> registry = p_203910_.f_206234_();
      TagLoader<Holder<T>> tagloader = new TagLoader<>((p_203914_) -> {
         return registry.m_203636_(ResourceKey.create(resourcekey, p_203914_));
      }, m_203918_(resourcekey));
      return CompletableFuture.supplyAsync(() -> {
         return new TagManager.LoadResult<>(resourcekey, tagloader.m_203900_(p_203908_));
      }, p_203909_);
   }

   public static record LoadResult<T>(ResourceKey<? extends Registry<T>> f_203928_, Map<ResourceLocation, Tag<Holder<T>>> f_203929_) {
   }
}
