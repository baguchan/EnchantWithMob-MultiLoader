package com.infamousmisadventures.infamousexample.registry;

import com.google.common.collect.ImmutableList;
import com.infamousmisadventures.infamousexample.platform.Services;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static com.infamousmisadventures.infamousexample.util.ResourceLocationHelper.modLoc;

public class ExampleBlocks {
    private static final ObjectArrayList<Supplier<Block>> BLOCKS = new ObjectArrayList<>();

    public static void register() {
    }

    public static ImmutableList<Supplier<Block>> getBlocks() {
        return ImmutableList.copyOf(BLOCKS);
    }

    private static Supplier<Block> registerBlock(String id, Supplier<Block> attribSup) {
        Supplier<Block> blockSupplier = Services.REGISTRAR.registerObject(modLoc(id), attribSup, BuiltInRegistries.BLOCK);
        BLOCKS.add(blockSupplier);
        return blockSupplier;
    }
}
