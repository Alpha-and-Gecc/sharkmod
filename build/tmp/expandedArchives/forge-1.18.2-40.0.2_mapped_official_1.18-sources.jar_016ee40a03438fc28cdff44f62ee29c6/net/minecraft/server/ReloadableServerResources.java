package net.minecraft.server;

import com.mojang.logging.LogUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import net.minecraft.commands.Commands;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleReloadInstance;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagManager;
import net.minecraft.util.Unit;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.ItemModifierManager;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.PredicateManager;
import org.slf4j.Logger;

public class ReloadableServerResources {
   private static final Logger f_206845_ = LogUtils.getLogger();
   private static final CompletableFuture<Unit> f_206846_ = CompletableFuture.completedFuture(Unit.INSTANCE);
   private final Commands f_206847_;
   private final RecipeManager f_206848_ = new RecipeManager();
   private final TagManager f_206849_;
   private final PredicateManager f_206850_ = new PredicateManager();
   private final LootTables f_206851_ = new LootTables(this.f_206850_);
   private final ItemModifierManager f_206852_ = new ItemModifierManager(this.f_206850_, this.f_206851_);
   private final ServerAdvancementManager f_206853_ = new ServerAdvancementManager(this.f_206850_);
   private final ServerFunctionLibrary f_206854_;

   public ReloadableServerResources(RegistryAccess.Frozen p_206857_, Commands.CommandSelection p_206858_, int p_206859_) {
      this.f_206849_ = new TagManager(p_206857_);
      this.f_206847_ = new Commands(p_206858_);
      this.f_206854_ = new ServerFunctionLibrary(p_206859_, this.f_206847_.getDispatcher());
   }

   public ServerFunctionLibrary m_206860_() {
      return this.f_206854_;
   }

   public PredicateManager m_206881_() {
      return this.f_206850_;
   }

   public LootTables m_206885_() {
      return this.f_206851_;
   }

   public ItemModifierManager m_206886_() {
      return this.f_206852_;
   }

   public RecipeManager m_206887_() {
      return this.f_206848_;
   }

   public Commands m_206888_() {
      return this.f_206847_;
   }

   public ServerAdvancementManager m_206889_() {
      return this.f_206853_;
   }

   public List<PreparableReloadListener> m_206890_() {
      return List.of(this.f_206849_, this.f_206850_, this.f_206848_, this.f_206851_, this.f_206852_, this.f_206854_, this.f_206853_);
   }

   public static CompletableFuture<ReloadableServerResources> m_206861_(ResourceManager p_206862_, RegistryAccess.Frozen p_206863_, Commands.CommandSelection p_206864_, int p_206865_, Executor p_206866_, Executor p_206867_) {
      ReloadableServerResources reloadableserverresources = new ReloadableServerResources(p_206863_, p_206864_, p_206865_);
      List<PreparableReloadListener> listeners = new java.util.ArrayList<>(reloadableserverresources.m_206890_());
      listeners.addAll(net.minecraftforge.event.ForgeEventFactory.onResourceReload());
      return SimpleReloadInstance.m_203834_(p_206862_, listeners, p_206866_, p_206867_, f_206846_, f_206845_.isDebugEnabled()).done().thenApply((p_206880_) -> {
         return reloadableserverresources;
      });
   }

   public void m_206868_(RegistryAccess p_206869_) {
      this.f_206849_.m_203904_().forEach((p_206884_) -> {
         m_206870_(p_206869_, p_206884_);
      });
      Blocks.rebuildCache();
      net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.TagsUpdatedEvent(p_206869_));
   }

   private static <T> void m_206870_(RegistryAccess p_206871_, TagManager.LoadResult<T> p_206872_) {
      ResourceKey<? extends Registry<T>> resourcekey = p_206872_.f_203928_();
      Map<TagKey<T>, List<Holder<T>>> map = p_206872_.f_203929_().entrySet().stream().collect(Collectors.toUnmodifiableMap((p_206877_) -> {
         return TagKey.m_203882_(resourcekey, p_206877_.getKey());
      }, (p_206874_) -> {
         return p_206874_.getValue().getValues();
      }));
      p_206871_.registryOrThrow(resourcekey).m_203652_(map);
   }
}
