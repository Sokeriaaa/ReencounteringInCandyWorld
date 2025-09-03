package sokeriaaa.candyworld.platform.neoforge.datagen.providers

import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.platform.neoforge.impl.CandyWorldRegistryImpl
import java.util.concurrent.CompletableFuture

class CandyDatapackBuiltinEntries(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
) : DatapackBuiltinEntriesProvider(
    output,
    lookupProvider,
    RegistrySetBuilder()
        .add(Registries.CONFIGURED_FEATURE) { context ->
            (Platform.registry as CandyWorldRegistryImpl)
                .featureMap
                .forEach { (key, value) ->
                    context.register(
                        value.configured.key,
                        value.configured.value,
                    )
                }
        }.add(Registries.PLACED_FEATURE) { context ->
            (Platform.registry as CandyWorldRegistryImpl)
                .featureMap
                .forEach { (key, value) ->
                    context.register(
                        value.placed.key,
                        value.placed.value,
                    )
                }
        }.add(Registries.BIOME) { context ->
            (Platform.registry as CandyWorldRegistryImpl)
                .biomeMap
                .forEach { (key, value) ->
                    context.register(
                        key,
                        value.value(
                            context.lookup(Registries.PLACED_FEATURE),
                            context.lookup(Registries.CONFIGURED_CARVER),
                        ).build()
                    )
                }
        },
    setOf(CandyWorld.MOD_ID)
)