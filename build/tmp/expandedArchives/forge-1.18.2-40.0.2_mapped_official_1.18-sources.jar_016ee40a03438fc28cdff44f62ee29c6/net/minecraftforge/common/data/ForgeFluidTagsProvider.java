/*
 * Minecraft Forge - Forge Development LLC
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.minecraftforge.common.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.ForgeMod;

import static net.minecraftforge.common.Tags.Fluids.MILK;

public final class ForgeFluidTagsProvider extends FluidTagsProvider
{
    public ForgeFluidTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper)
    {
        super(gen, "forge", existingFileHelper);
    }

    @Override
    public void addTags()
    {
        m_206424_(MILK).addOptional(ForgeMod.MILK.getId()).addOptional(ForgeMod.FLOWING_MILK.getId());
    }

    @Override
    public String getName()
    {
        return "Forge Fluid Tags";
    }
}
