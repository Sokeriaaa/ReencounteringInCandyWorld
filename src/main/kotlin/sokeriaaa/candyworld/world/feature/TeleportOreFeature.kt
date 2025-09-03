package sokeriaaa.candyworld.world.feature

import com.mojang.serialization.Codec
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.OreFeature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import sokeriaaa.candyworld.CandyWorld

open class TeleportOreFeature(codec: Codec<OreConfiguration>) : OreFeature(codec) {

    override fun place(
        featurePlaceContext: FeaturePlaceContext<OreConfiguration>
    ): Boolean {
        if (
            featurePlaceContext.level().level
                .dimension()
                .location() == CandyWorld.id("candy_world")
        ) {
            return super.place(featurePlaceContext)
        }
        return false
    }
}