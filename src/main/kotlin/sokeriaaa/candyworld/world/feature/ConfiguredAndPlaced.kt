package sokeriaaa.candyworld.world.feature

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import sokeriaaa.candyworld.registry.KeyObjectPair

data class ConfiguredAndPlaced(
    val configuredLazy: Lazy<KeyObjectPair<ConfiguredFeature<*, *>>>,
    val placedLazy: Lazy<KeyObjectPair<PlacedFeature>>
) {
    val configured: KeyObjectPair<ConfiguredFeature<*, *>> get() = configuredLazy.value
    val placed: KeyObjectPair<PlacedFeature> get() = placedLazy.value
    val path get() = configured.path
}