package sokeriaaa.candyworld.world

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import sokeriaaa.candyworld.registry.RegistryObject
import sokeriaaa.candyworld.world.tree.placer.ChocolateFoliagePlacer
import sokeriaaa.candyworld.world.tree.placer.CottonCandyFoliagePlacer

object ModFoliagePlacer {

    val COTTON_CANDY_FOLIAGE_PLACER = foliagePlacerType("cotton_candy_foliage_placer") {
        FoliagePlacerType(CottonCandyFoliagePlacer.CODEC)
    }
    val CHOCOLATE_FOLIAGE_PLACER = foliagePlacerType("chocolate_foliage_placer") {
        FoliagePlacerType(ChocolateFoliagePlacer.CODEC)
    }

    private inline fun <reified T : FoliagePlacer> foliagePlacerType(
        path: String,
        noinline supplier: () -> FoliagePlacerType<T>,
    ) = RegistryObject(
        path = path,
        lazy = lazy(supplier),
    )

    fun register() {
        CandyWorldRegistry {
            registerFoliagePlacer(COTTON_CANDY_FOLIAGE_PLACER)
            registerFoliagePlacer(CHOCOLATE_FOLIAGE_PLACER)
        }
    }
}