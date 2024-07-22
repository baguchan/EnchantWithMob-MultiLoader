package bagu_chan.enchantwithmob.mixins;

import bagu_chan.enchantwithmob.utils.ModSpawnUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(value = NaturalSpawner.class)
public class NaturalSpawnerMixin {
    @Inject(
            method = "spawnCategoryForPosition(Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/NaturalSpawner$SpawnPredicate;Lnet/minecraft/world/level/NaturalSpawner$AfterSpawnCallback;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/NaturalSpawner;isValidPositionForMob(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Mob;D)Z",
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT // SOFT, because this will break in 2 seconds
    )
    private static void spawnNatural(MobCategory category, ServerLevel level, ChunkAccess chunk, BlockPos pos, NaturalSpawner.SpawnPredicate filter, NaturalSpawner.AfterSpawnCallback callback, CallbackInfo ci, StructureManager structureManager, ChunkGenerator chunkGenerator, int i, BlockState blockState, BlockPos.MutableBlockPos mutableBlockPos, int j, int k, int l, int m, int n, MobSpawnSettings.SpawnerData spawnerData, SpawnGroupData spawnGroupData, int o, int p, int q, double d, double e, Player player, double f, Mob mob) {

        ModSpawnUtil.finalizeSpawn(mob, level, MobSpawnType.NATURAL);
    }

    @Inject(
            method = "spawnMobsForChunkGeneration",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;checkSpawnRules(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;)Z",
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT // SOFT, because this will break in 2 seconds
    )
    private static void spawnChunk(ServerLevelAccessor levelAccessor, Holder<Biome> biome, ChunkPos chunkPos, RandomSource random, CallbackInfo ci, MobSpawnSettings mobSpawnSettings, WeightedRandomList weightedRandomList, int i, int j, Optional optional, MobSpawnSettings.SpawnerData spawnerData, int k, SpawnGroupData spawnGroupData, int l, int m, int n, int o, int p, boolean bl, int q, BlockPos blockPos, float f, double d, double e, Entity entity, Mob mob) {

        ModSpawnUtil.finalizeSpawn(mob, levelAccessor, MobSpawnType.CHUNK_GENERATION);
    }
}
