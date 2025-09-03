package sokeriaaa.candyworld.world.region

import com.mojang.datafixers.util.Pair
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.biome.Climate
import sokeriaaa.candyworld.registry.ModBiomes
import terrablender.api.Region
import terrablender.api.RegionType
import java.util.function.Consumer


class ChocolateForestRegion(
    name: ResourceLocation,
    weight: Int,
) : Region(name, RegionType.OVERWORLD, weight) {

    override fun addBiomes(
        registry: Registry<Biome>,
        mapper: Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>>,
    ) {
        addModifiedVanillaOverworldBiomes(mapper) {
            it.replaceBiome(Biomes.FOREST, ModBiomes.CHOCOLATE_FOREST)
        }
    }

}