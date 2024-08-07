package bagu_chan.enchantwithmob.registry;

import bagu_chan.enchantwithmob.entity.Enchanter;
import bagu_chan.enchantwithmob.platform.Services;
import bagu_chan.enchantwithmob.utils.ResourceLocationHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

import static bagu_chan.enchantwithmob.utils.ResourceLocationHelper.modLoc;

public class EWEntityTypes {
    public static final Supplier<EntityType<Enchanter>> ENCHANTER = create("enchanter", () -> EntityType.Builder.of(Enchanter::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(ResourceLocationHelper.modLoc("enchanter").toString()));

    public static void register() {
        Services.MOB_REGISTRY.attributes(ENCHANTER, Enchanter::createAttributeMap);
        Services.MOB_REGISTRY.registrySpawnPlacement(ENCHANTER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

    private static <T extends Entity, E extends EntityType<? extends T>> Supplier<E> create(String key, Supplier<E> builder) {
        return Services.REGISTRAR.registerObject(modLoc(key), builder, BuiltInRegistries.ENTITY_TYPE);
    }
}