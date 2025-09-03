package sokeriaaa.candyworld.platform.neoforge.impl

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.platform.fluid.CandyWorldFluid
import sokeriaaa.candyworld.platform.neoforge.impl.CandyWorldFluidImpl.Companion.toFluidType
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import sokeriaaa.candyworld.registry.CandyBiomeBuilder
import sokeriaaa.candyworld.registry.ModCreativeModeTabs
import sokeriaaa.candyworld.registry.RegistryObject
import sokeriaaa.candyworld.world.feature.ConfiguredAndPlaced
import java.util.function.Supplier

class CandyWorldRegistryImpl : CandyWorldRegistry {

    // Create a Deferred Register to hold Items which will all be registered under the "candyworld" namespace
    val ITEMS: DeferredRegister.Items =
        DeferredRegister.createItems(CandyWorld.MOD_ID)

    // Create a Deferred Register to hold Blocks which will all be registered under the "candyworld" namespace
    val BLOCKS: DeferredRegister.Blocks =
        DeferredRegister.createBlocks(CandyWorld.MOD_ID)

    // Create a Deferred Register to hold Items which will all be registered under the "candyworld" namespace
    val FLUID_TYPES: DeferredRegister<FluidType> =
        DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, CandyWorld.MOD_ID)

    // Create a Deferred Register to hold Items which will all be registered under the "candyworld" namespace
    val FLUIDS: DeferredRegister<Fluid> =
        DeferredRegister.create(Registries.FLUID, CandyWorld.MOD_ID)

    // Create a Deferred Register to hold EntityTypes which will all be registered under the "candyworld" namespace
    val ENTITY_TYPES: DeferredRegister<EntityType<*>> =
        DeferredRegister.create(Registries.ENTITY_TYPE, CandyWorld.MOD_ID)

    // Create a Deferred Register to hold SoundEvent which will all be registered under the "candyworld" namespace
    val SOUND_EVENTS: DeferredRegister<SoundEvent> =
        DeferredRegister.create(Registries.SOUND_EVENT, CandyWorld.MOD_ID)

    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab?> =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CandyWorld.MOD_ID)

    val FEATURES: DeferredRegister<Feature<*>> =
        DeferredRegister.create(Registries.FEATURE, CandyWorld.MOD_ID)

    val FOLIAGE_PLACERS: DeferredRegister<FoliagePlacerType<*>> =
        DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, CandyWorld.MOD_ID)

    private val _fluidTypeMap: MutableMap<String, FluidType> = HashMap()
    val fluidTypeMap: Map<String, FluidType> get() = _fluidTypeMap

    private val _featureMap: MutableMap<String, ConfiguredAndPlaced> = HashMap()
    val featureMap: Map<String, ConfiguredAndPlaced> get() = _featureMap

    private val _biomeMap: MutableMap<ResourceKey<Biome>, RegistryObject<CandyBiomeBuilder>> = HashMap()
    val biomeMap: Map<ResourceKey<Biome>, RegistryObject<CandyBiomeBuilder>> get() = _biomeMap

    override fun <T : Item> registerItem(
        registryObject: RegistryObject<T>,
        creativeModeTab: ModCreativeModeTabs.Wrapper?,
    ) {
        ITEMS.register(
            registryObject.path,
            Supplier {
                registryObject.value.also { item ->
                    creativeModeTab?.addItem(item)
                }
            }
        )
    }

    override fun <T : Block> registerBlock(
        registryObject: RegistryObject<T>,
        creativeModeTab: ModCreativeModeTabs.Wrapper?,
    ) {
        BLOCKS.register(registryObject.path, Supplier { registryObject.value })
        creativeModeTab?.let {
            ITEMS.register(
                registryObject.path,
                Supplier {
                    BlockItem(
                        registryObject.value,
                        Item.Properties(),
                    ).also {
                        // registerCreativeModeTab
                        creativeModeTab.addItem(it)
                    }
                }
            )
        }
    }

    override fun registerFluidType(attributes: CandyWorldFluid.Attributes) {
        val fluidType = attributes.toFluidType()
        _fluidTypeMap[attributes.path] = fluidType
        FLUID_TYPES.register(
            attributes.path,
            Supplier { fluidType },
        )
    }

    override fun <T : FlowingFluid> registerFluid(
        registryObject: RegistryObject<T>,
    ) {
        FLUIDS.register(
            registryObject.path,
            Supplier { registryObject.value },
        )
    }

    override fun registerCreativeModeTab(
        creativeModeTab: ModCreativeModeTabs.Wrapper
    ) {
        CREATIVE_MODE_TABS.register(
            creativeModeTab.resourceLocation.path,
            Supplier { creativeModeTab.value },
        )
    }

    override fun <T : Entity> registerEntityType(
        registryObject: RegistryObject<EntityType<T>>,
    ) {
        ENTITY_TYPES.register(
            registryObject.path,
            Supplier { registryObject.value },
        )
    }

    override fun registerSoundEvent(
        path: String,
        soundEvent: SoundEvent,
    ) {
        SOUND_EVENTS.register(
            path,
            Supplier { soundEvent },
        )
    }

    override fun <FC, T : Feature<FC>> registerFeature(registryObject: RegistryObject<T>) {
        FEATURES.register(
            registryObject.path,
            Supplier { registryObject.value },
        )
    }

    override fun <T : FoliagePlacer> registerFoliagePlacer(registryObject: RegistryObject<FoliagePlacerType<T>>) {
        FOLIAGE_PLACERS.register(
            registryObject.path,
            Supplier { registryObject.value },
        )
    }

    override fun registerConfiguredAndPlacedFeature(configuredAndPlacedFeature: ConfiguredAndPlaced) {
        _featureMap[configuredAndPlacedFeature.path] = configuredAndPlacedFeature
    }

    override fun registerBiome(
        resourceKey: ResourceKey<Biome>,
        registryObject: RegistryObject<CandyBiomeBuilder>
    ) {
        _biomeMap[resourceKey] = registryObject
    }

    fun registerAllDeferredTo(eventBus: IEventBus) {
        FLUID_TYPES.register(eventBus)
        FLUIDS.register(eventBus)
        ITEMS.register(eventBus)
        BLOCKS.register(eventBus)
        ENTITY_TYPES.register(eventBus)
        SOUND_EVENTS.register(eventBus)
        FOLIAGE_PLACERS.register(eventBus)
        FEATURES.register(eventBus)

        ModCreativeModeTabs.register()
        CREATIVE_MODE_TABS.register(eventBus)
    }
}