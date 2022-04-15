/*
 * Minecraft Forge - Forge Development LLC
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.registries;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;

class NamespacedWrapper<T extends IForgeRegistryEntry<T>> extends MappedRegistry<T> implements ILockableRegistry
{
    private static final Logger LOGGER = LogManager.getLogger();
    private final ForgeRegistry<T> delegate;
    private final NamespacedHolderHelper<T> holders;

    private boolean locked = false;
    private Lifecycle elementsLifecycle = Lifecycle.experimental();

    public NamespacedWrapper(ForgeRegistry<T> owner, Function<T, Holder.Reference<T>> holderLookup)
    {
        super(owner.getRegistryKey(), Lifecycle.experimental(), holderLookup);
        this.delegate = owner;
        this.holders = new NamespacedHolderHelper<>(this, null, holderLookup);
    }

    @Override
    public Holder<T> m_203704_(int id, ResourceKey<T> key, T value, Lifecycle lifecycle)
    {
        if (locked)
            throw new IllegalStateException("Can not register to a locked registry. Modder should use Forge Register methods.");

        Validate.notNull(value);
        this.elementsLifecycle = this.elementsLifecycle.add(lifecycle);

        if (value.getRegistryName() == null)
            value.setRegistryName(key.location());

        T oldValue = get(value.getRegistryName());

        int realId = this.delegate.add(id, value);
        if (realId != id && id != -1)
            LOGGER.warn("Registered object did not get ID it asked for. Name: {} Type: {} Expected: {} Got: {}", key, value.getRegistryType().getName(), id, realId);

        return this.holders.onAdded(RegistryManager.ACTIVE, realId, value, oldValue);
    }

    @Override
    public Holder<T> m_203505_(ResourceKey<T> key, T value, Lifecycle lifecycle)
    {
        return m_203704_(-1, key, value, lifecycle);
    }

    @Override
    public Holder<T> m_203384_(OptionalInt id, ResourceKey<T> key, T value, Lifecycle lifecycle) {
        int wanted = -1;
        if (id.isPresent() && byId(id.getAsInt()) != null)
            wanted = id.getAsInt();
        return m_203704_(wanted, key, value, lifecycle);
    }

    // Reading Functions
    @Override
    @Nullable
    public T get(@Nullable ResourceLocation name)
    {
        return this.delegate.getRaw(name); //get without default
    }

    @Override
    public Optional<T> getOptional(@Nullable ResourceLocation name)
    {
        return Optional.ofNullable( this.delegate.getRaw(name)); //get without default
    }

    @Override
    @Nullable
    public T get(@Nullable ResourceKey<T> name)
    {
        return name == null ? null : this.delegate.getRaw(name.location()); //get without default
    }

    @Override
    @Nullable
    public ResourceLocation getKey(T value)
    {
        return this.delegate.getKey(value);
    }

    @Override
    public Optional<ResourceKey<T>> getResourceKey(T p_122755_)
    {
        return this.delegate.getResourceKey(p_122755_);
    }

    @Override
    public boolean containsKey(ResourceLocation key)
    {
        return this.delegate.containsKey(key);
    }

    @Override
    public boolean containsKey(ResourceKey<T> key)
    {
       return this.delegate.getRegistryName().equals(key.m_211136_()) && containsKey(key.location());
    }

    @Override
    public int getId(@Nullable T value)
    {
        return this.delegate.getID(value);
    }

    @Override
    @Nullable
    public T byId(int id)
    {
        return this.delegate.getValue(id);
    }

    @Override
    public Lifecycle lifecycle(T value)
    {
        return Lifecycle.stable();
    }

    @Override
    public Lifecycle elementsLifecycle()
    {
       return this.elementsLifecycle;
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.delegate.iterator();
    }

    @Override
    public Set<ResourceLocation> keySet()
    {
        return this.delegate.getKeys();
    }

    @Override
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet()
    {
        return this.delegate.getEntries();
    }

    @Override
    public boolean isEmpty()
    {
        return this.delegate.isEmpty();
    }

    @Override
    public int size()
    {
        return this.delegate.size();
    }

    @Override public Optional<Holder<T>> m_203300_(int id) { return this.holders.getHolder(id); }
    @Override public Optional<Holder<T>> m_203636_(ResourceKey<T> key) { return this.holders.getHolder(key); }
    @Override public Holder<T> m_203538_(ResourceKey<T> key) { return this.holders.getOrCreateHolder(key); }
    @Override public Optional<Holder<T>> m_203454_(Random rand) { return this.holders.getRandom(rand); }
    @Override public Stream<Holder.Reference<T>> m_203611_() { return this.holders.holders();  }
    @Override public boolean m_203658_(TagKey<T> name) { return this.holders.isKnownTagName(name); }
    @Override public Stream<Pair<TagKey<T>, HolderSet.Named<T>>> m_203612_() { return this.holders.getTags(); }
    @Override public HolderSet.Named<T> m_203561_(TagKey<T> name) { return this.holders.getOrCreateTag(name); }
    @Override public Stream<TagKey<T>> m_203613_() { return this.holders.getTagNames(); }
    @Override public Registry<T> m_203521_() { return this.holders.freeze(); }
    @Override public Holder.Reference<T> m_203693_(T value) { return this.holders.createIntrusiveHolder(value); }
    @Override public Optional<HolderSet.Named<T>> m_203431_(TagKey<T> name) { return this.holders.getTag(name); }
    @Override public void m_203652_(Map<TagKey<T>, List<Holder<T>>> newTags) { this.holders.bindTags(newTags); }
    @Override public void m_203635_() { this.holders.resetTags(); }
    @Deprecated @Override public void unfreeze() { this.holders.unfreeze(); }

    /** @Depreciated Forge: For internal use only. Use the Register events when registering values. */
    @Deprecated @Override public void lock(){ this.locked = true; }


    public static class Factory<V extends IForgeRegistryEntry<V>> implements IForgeRegistry.CreateCallback<V>, IForgeRegistry.AddCallback<V>
    {
        public static final ResourceLocation ID = new ResourceLocation("forge", "registry_defaulted_wrapper");

        @Override
        public void onCreate(IForgeRegistryInternal<V> owner, RegistryManager stage)
        {
            ForgeRegistry<V> fowner = (ForgeRegistry<V>)owner;
            owner.setSlaveMap(ID, new NamespacedWrapper<V>(fowner, fowner.getBuilder().getVanillaHolder()));
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onAdd(IForgeRegistryInternal<V> owner, RegistryManager stage, int id, V value, V oldValue)
        {
            owner.getSlaveMap(ID, NamespacedWrapper.class).holders.onAdded(stage, id, value, oldValue);
        }
    }
}
