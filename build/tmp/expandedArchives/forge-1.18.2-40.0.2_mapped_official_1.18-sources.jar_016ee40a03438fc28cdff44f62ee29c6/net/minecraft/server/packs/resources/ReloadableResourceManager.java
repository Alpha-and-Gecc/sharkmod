package net.minecraft.server.packs.resources;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.util.Unit;
import org.slf4j.Logger;

public class ReloadableResourceManager implements ResourceManager, AutoCloseable {
   private static final Logger f_203814_ = LogUtils.getLogger();
   private CloseableResourceManager f_203815_;
   private final List<PreparableReloadListener> f_203816_ = Lists.newArrayList();
   private final PackType f_203817_;

   public ReloadableResourceManager(PackType p_203820_) {
      this.f_203817_ = p_203820_;
      this.f_203815_ = new MultiPackResourceManager(p_203820_, List.of());
   }

   public void close() {
      this.f_203815_.close();
   }

   public void registerReloadListener(PreparableReloadListener p_10714_) {
      this.f_203816_.add(p_10714_);
   }

   public ReloadInstance createReload(Executor p_143930_, Executor p_143931_, CompletableFuture<Unit> p_143932_, List<PackResources> p_143933_) {
      f_203814_.info("Reloading ResourceManager: {}", LogUtils.defer(() -> {
         return p_143933_.stream().map(PackResources::getName).collect(Collectors.joining(", "));
      }));
      this.f_203815_.close();
      this.f_203815_ = new MultiPackResourceManager(this.f_203817_, p_143933_);
      return SimpleReloadInstance.m_203834_(this.f_203815_, this.f_203816_, p_143930_, p_143931_, p_143932_, f_203814_.isDebugEnabled());
   }

   public Resource getResource(ResourceLocation p_203833_) throws IOException {
      return this.f_203815_.getResource(p_203833_);
   }

   public Set<String> getNamespaces() {
      return this.f_203815_.getNamespaces();
   }

   public boolean hasResource(ResourceLocation p_203828_) {
      return this.f_203815_.hasResource(p_203828_);
   }

   public List<Resource> getResources(ResourceLocation p_203831_) throws IOException {
      return this.f_203815_.getResources(p_203831_);
   }

   public Collection<ResourceLocation> listResources(String p_203823_, Predicate<String> p_203824_) {
      return this.f_203815_.listResources(p_203823_, p_203824_);
   }

   public Stream<PackResources> listPacks() {
      return this.f_203815_.listPacks();
   }
}