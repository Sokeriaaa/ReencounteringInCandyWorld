package sokeriaaa.candyworld.registry

import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.level.levelgen.Heightmap
import sokeriaaa.candyworld.entity.CandySheep
import sokeriaaa.candyworld.entity.EasterChicken
import sokeriaaa.candyworld.entity.GummyBear
import sokeriaaa.candyworld.entity.GummyMouse
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import kotlin.reflect.KClass

object ModEntities {

    val COTTON_CANDY_SHEEP = entityType("cotton_candy_sheep") {
        EntityType.Builder.of(
            { type, level -> CandySheep(type, level) },
            MobCategory.CREATURE,
        ).sized(0.9F, 1.3F)
            .clientTrackingRange(10)
            .build("cotton_candy_sheep")
    }

    val EASTER_CHICKEN = entityType("easter_chicken") {
        EntityType.Builder.of(
            { type, level -> EasterChicken(type, level) },
            MobCategory.CREATURE,
        ).sized(0.4F, 0.7F)
            .clientTrackingRange(10)
            .build("easter_chicken")
    }

    val GUMMY_MOUSE = entityType("gummy_mouse") {
        EntityType.Builder.of(
            { type, level -> GummyMouse(type, level) },
            MobCategory.CREATURE,
        ).sized(0.5F, 0.4F)
            .clientTrackingRange(10)
            .build("gummy_mouse")
    }

    val GUMMY_BEAR = entityType("gummy_bear") {
        EntityType.Builder.of(
            { type, level -> GummyBear(type, level) },
            MobCategory.CREATURE,
        ).sized(1.4F, 1.4F)
            .clientTrackingRange(10)
            .build("gummy_bear")
    }

    private inline fun <reified T : EntityType<out Mob>> entityType(
        path: String,
        noinline supplier: () -> T,
    ) = RegistryObject(
        path = path,
        lazy = lazy(supplier),
    )

    fun registerSpawnPlacements(
        registerSpawnPlacement: (SpawnPlacementsWrapper<*>) -> Unit
    ) {
        registerSpawnPlacement(
            SpawnPlacementsWrapper(
                COTTON_CANDY_SHEEP.value,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                CandySheep::canSheepSpawn,
            )
        )
        registerSpawnPlacement(
            SpawnPlacementsWrapper(
                EASTER_CHICKEN.value,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EasterChicken::canChickenSpawn,
            )
        )
        registerSpawnPlacement(
            SpawnPlacementsWrapper(
                GUMMY_MOUSE.value,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                GummyMouse::canGummySpawn,
            )
        )
        registerSpawnPlacement(
            SpawnPlacementsWrapper(
                GUMMY_BEAR.value,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                GummyBear::canGummySpawn,
            )
        )
    }

    fun registerEntityAttributes(
        registerEntityAttribute: (EntityType<*>, AttributeSupplier.Builder) -> Unit
    ) {
        registerEntityAttribute(COTTON_CANDY_SHEEP.value, CandySheep.registerAttributes())
        registerEntityAttribute(EASTER_CHICKEN.value, EasterChicken.registerAttributes())
        registerEntityAttribute(GUMMY_MOUSE.value, GummyMouse.registerAttributes())
        registerEntityAttribute(GUMMY_BEAR.value, GummyBear.registerAttributes())
    }

    data class SpawnPlacementsWrapper<T : Entity>(
        val entityType: EntityType<T>,
        val spawnPlacementType: SpawnPlacementType,
        val heightMapType: Heightmap.Types,
        val spawnPredicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        val entityTypeClass: KClass<EntityType<T>> =
            entityType::class as KClass<EntityType<T>>
        val spawnPredicateClass: KClass<SpawnPlacements.SpawnPredicate<T>> =
            spawnPredicate::class as KClass<SpawnPlacements.SpawnPredicate<T>>
    }

    fun register() {
        CandyWorldRegistry {
            registerEntityType(COTTON_CANDY_SHEEP)
            registerEntityType(EASTER_CHICKEN)
            registerEntityType(GUMMY_MOUSE)
            registerEntityType(GUMMY_BEAR)
        }
    }
}