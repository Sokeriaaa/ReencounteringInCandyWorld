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

class CottonCandyPlainsRegion(
    name: ResourceLocation,
    weight: Int,
) : Region(name, RegionType.OVERWORLD, weight) {

    override fun addBiomes(
        registry: Registry<Biome>,
        mapper: Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>>,
    ) {
        addModifiedVanillaOverworldBiomes(mapper) {
            it.replaceBiome(Biomes.PLAINS, ModBiomes.COTTON_CANDY_PLAINS)
        }
//        val builder = VanillaParameterOverlayBuilder()
//        ParameterUtils.ParameterPointListBuilder()
//            // 温度 Temperature
//            .temperature(ParameterUtils.Temperature.WARM,ParameterUtils.Temperature.HOT)
//            // 湿度 Humidity
//            .humidity(ParameterUtils.Humidity.NEUTRAL,ParameterUtils.Humidity.WET)
//            // 大陆性 Continentalness
//            .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
//            // 侵蚀度 Erosion
//            .erosion(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1)
//            // 深度 Depth
//            .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
//            // 怪异度 Weirdness
//            .weirdness(
//                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
//                ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING,
//            )
//            // 偏移量，防止生物群系完全集中在一点 Offset
//            .offset(0.1f)
//            .build()
//            .forEach {
//                builder.add(it, ModBiomes.COTTON_CANDY_PLAINS)
//            }
//        builder.build().forEach(mapper)
    }

}