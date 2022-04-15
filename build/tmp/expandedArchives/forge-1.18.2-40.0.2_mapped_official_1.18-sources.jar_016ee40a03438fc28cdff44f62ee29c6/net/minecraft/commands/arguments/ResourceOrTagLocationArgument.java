package net.minecraft.commands.arguments;

import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.datafixers.util.Either;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.ArgumentSerializer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public class ResourceOrTagLocationArgument<T> implements ArgumentType<ResourceOrTagLocationArgument.Result<T>> {
   private static final Collection<String> f_210943_ = Arrays.asList("foo", "foo:bar", "012", "#skeletons", "#minecraft:skeletons");
   private static final DynamicCommandExceptionType f_210944_ = new DynamicCommandExceptionType((p_210974_) -> {
      return new TranslatableComponent("commands.locatebiome.invalid", p_210974_);
   });
   private static final DynamicCommandExceptionType f_210945_ = new DynamicCommandExceptionType((p_210967_) -> {
      return new TranslatableComponent("commands.locate.invalid", p_210967_);
   });
   final ResourceKey<? extends Registry<T>> f_210946_;

   public ResourceOrTagLocationArgument(ResourceKey<? extends Registry<T>> p_210949_) {
      this.f_210946_ = p_210949_;
   }

   public static <T> ResourceOrTagLocationArgument<T> m_210968_(ResourceKey<? extends Registry<T>> p_210969_) {
      return new ResourceOrTagLocationArgument<>(p_210969_);
   }

   private static <T> ResourceOrTagLocationArgument.Result<T> m_210955_(CommandContext<CommandSourceStack> p_210956_, String p_210957_, ResourceKey<Registry<T>> p_210958_, DynamicCommandExceptionType p_210959_) throws CommandSyntaxException {
      ResourceOrTagLocationArgument.Result<?> result = p_210956_.getArgument(p_210957_, ResourceOrTagLocationArgument.Result.class);
      Optional<ResourceOrTagLocationArgument.Result<T>> optional = result.m_207209_(p_210958_);
      return optional.orElseThrow(() -> {
         return p_210959_.create(result);
      });
   }

   public static ResourceOrTagLocationArgument.Result<Biome> m_210952_(CommandContext<CommandSourceStack> p_210953_, String p_210954_) throws CommandSyntaxException {
      return m_210955_(p_210953_, p_210954_, Registry.BIOME_REGISTRY, f_210944_);
   }

   public static ResourceOrTagLocationArgument.Result<ConfiguredStructureFeature<?, ?>> m_210970_(CommandContext<CommandSourceStack> p_210971_, String p_210972_) throws CommandSyntaxException {
      return m_210955_(p_210971_, p_210972_, Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, f_210945_);
   }

   public ResourceOrTagLocationArgument.Result<T> parse(StringReader p_210951_) throws CommandSyntaxException {
      if (p_210951_.canRead() && p_210951_.peek() == '#') {
         int i = p_210951_.getCursor();

         try {
            p_210951_.skip();
            ResourceLocation resourcelocation1 = ResourceLocation.read(p_210951_);
            return new ResourceOrTagLocationArgument.TagResult<>(TagKey.m_203882_(this.f_210946_, resourcelocation1));
         } catch (CommandSyntaxException commandsyntaxexception) {
            p_210951_.setCursor(i);
            throw commandsyntaxexception;
         }
      } else {
         ResourceLocation resourcelocation = ResourceLocation.read(p_210951_);
         return new ResourceOrTagLocationArgument.ResourceResult<>(ResourceKey.create(this.f_210946_, resourcelocation));
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_210977_, SuggestionsBuilder p_210978_) {
      Object object = p_210977_.getSource();
      if (object instanceof SharedSuggestionProvider) {
         SharedSuggestionProvider sharedsuggestionprovider = (SharedSuggestionProvider)object;
         return sharedsuggestionprovider.m_212095_(this.f_210946_, SharedSuggestionProvider.ElementSuggestionType.ALL, p_210978_, p_210977_);
      } else {
         return p_210978_.buildFuture();
      }
   }

   public Collection<String> getExamples() {
      return f_210943_;
   }

   static record ResourceResult<T>(ResourceKey<T> f_210981_) implements ResourceOrTagLocationArgument.Result<T> {
      public Either<ResourceKey<T>, TagKey<T>> m_207418_() {
         return Either.left(this.f_210981_);
      }

      public <E> Optional<ResourceOrTagLocationArgument.Result<E>> m_207209_(ResourceKey<? extends Registry<E>> p_210988_) {
         return this.f_210981_.cast(p_210988_).map(ResourceOrTagLocationArgument.ResourceResult::new);
      }

      public boolean test(Holder<T> p_210986_) {
         return p_210986_.m_203565_(this.f_210981_);
      }

      public String m_207276_() {
         return this.f_210981_.location().toString();
      }
   }

   public interface Result<T> extends Predicate<Holder<T>> {
      Either<ResourceKey<T>, TagKey<T>> m_207418_();

      <E> Optional<ResourceOrTagLocationArgument.Result<E>> m_207209_(ResourceKey<? extends Registry<E>> p_210997_);

      String m_207276_();
   }

   public static class Serializer implements ArgumentSerializer<ResourceOrTagLocationArgument<?>> {
      public void serializeToNetwork(ResourceOrTagLocationArgument<?> p_211009_, FriendlyByteBuf p_211010_) {
         p_211010_.writeResourceLocation(p_211009_.f_210946_.location());
      }

      public ResourceOrTagLocationArgument<?> deserializeFromNetwork(FriendlyByteBuf p_211012_) {
         ResourceLocation resourcelocation = p_211012_.readResourceLocation();
         return new ResourceOrTagLocationArgument(ResourceKey.createRegistryKey(resourcelocation));
      }

      public void serializeToJson(ResourceOrTagLocationArgument<?> p_211006_, JsonObject p_211007_) {
         p_211007_.addProperty("registry", p_211006_.f_210946_.location().toString());
      }
   }

   static record TagResult<T>(TagKey<T> f_211015_) implements ResourceOrTagLocationArgument.Result<T> {
      public Either<ResourceKey<T>, TagKey<T>> m_207418_() {
         return Either.right(this.f_211015_);
      }

      public <E> Optional<ResourceOrTagLocationArgument.Result<E>> m_207209_(ResourceKey<? extends Registry<E>> p_211022_) {
         return this.f_211015_.m_207647_(p_211022_).map(ResourceOrTagLocationArgument.TagResult::new);
      }

      public boolean test(Holder<T> p_211020_) {
         return p_211020_.m_203656_(this.f_211015_);
      }

      public String m_207276_() {
         return "#" + this.f_211015_.f_203868_();
      }
   }
}