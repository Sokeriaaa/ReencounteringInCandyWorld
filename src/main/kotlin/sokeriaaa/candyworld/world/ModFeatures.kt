package sokeriaaa.candyworld.world

import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature
import net.minecraft.world.level.levelgen.feature.configurations.*
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import sokeriaaa.candyworld.registry.RegistryObject
import sokeriaaa.candyworld.world.feature.*
import sokeriaaa.candyworld.world.feature.config.CandySpikeConfiguration

object ModFeatures {

    val GUMMY_WORM = feature("gummy_worm") {
        GummyWormFeature(NoneFeatureConfiguration.CODEC)
    }

    val TELEPORT_ORE = feature("teleport_ore") {
        TeleportOreFeature(OreConfiguration.CODEC)
    }

    val CANDY_TREE = feature("candy_tree") {
        CandyTreeFeature(TreeConfiguration.CODEC)
    }

    val RANDOM_ROTATED_PATCH = feature("random_rotated_patch") {
        RandomPatchFeature(RandomPatchConfiguration.CODEC)
    }

    val CANDY_SPIKE = feature("candy_spike") {
        CandySpikeFeature(CandySpikeConfiguration.CODEC)
    }

    val CANDY_CANE = feature("candy_cane") {
        CandyCaneFeature(RandomPatchConfiguration.CODEC)
    }

    fun register() {
        CandyWorldRegistry {
            registerFeature(GUMMY_WORM)
            registerFeature(TELEPORT_ORE)
            registerFeature(CANDY_TREE)
            registerFeature(RANDOM_ROTATED_PATCH)
            registerFeature(CANDY_SPIKE)
            registerFeature(CANDY_CANE)
        }
    }

    private inline fun <reified T : FeatureConfiguration> feature(
        path: String,
        noinline supplier: () -> Feature<T>,
    ) = RegistryObject(
        path = path,
        lazy = lazy(supplier),
    )
}