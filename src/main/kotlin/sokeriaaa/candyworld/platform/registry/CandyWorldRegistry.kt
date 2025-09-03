package sokeriaaa.candyworld.platform.registry

import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import net.minecraft.world.level.material.FlowingFluid
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.platform.fluid.CandyWorldFluid
import sokeriaaa.candyworld.registry.CandyBiomeBuilder
import sokeriaaa.candyworld.registry.ModCreativeModeTabs
import sokeriaaa.candyworld.registry.RegistryObject
import sokeriaaa.candyworld.world.feature.ConfiguredAndPlaced

interface CandyWorldRegistry {

    fun <T : Item> registerItem(
        registryObject: RegistryObject<T>,
        creativeModeTab: ModCreativeModeTabs.Wrapper? = null,
    )

    fun <T : Block> registerBlock(
        registryObject: RegistryObject<T>,
        creativeModeTab: ModCreativeModeTabs.Wrapper? = null,
    )

    fun registerFluidType(
        attributes: CandyWorldFluid.Attributes,
    )

    fun <T : FlowingFluid> registerFluid(
        registryObject: RegistryObject<T>,
    )

    fun registerCreativeModeTab(
        creativeModeTab: ModCreativeModeTabs.Wrapper
    )

    fun <T : Entity> registerEntityType(
        registryObject: RegistryObject<EntityType<T>>,
    )

    fun <FC, T : Feature<FC>> registerFeature(
        registryObject: RegistryObject<T>,
    )

    fun <T : FoliagePlacer> registerFoliagePlacer(
        registryObject: RegistryObject<FoliagePlacerType<T>>,
    )

    fun registerConfiguredAndPlacedFeature(
        configuredAndPlacedFeature: ConfiguredAndPlaced,
    )

    fun registerBiome(
        resourceKey: ResourceKey<Biome>,
        registryObject: RegistryObject<CandyBiomeBuilder>,
    )

    fun registerSoundEvent(
        path: String,
        soundEvent: SoundEvent,
    )

    companion object {
        inline operator fun invoke(
            registerScope: CandyWorldRegistry.() -> Unit,
        ) {
            Platform.registry.registerScope()
        }
    }

}