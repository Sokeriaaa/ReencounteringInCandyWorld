package sokeriaaa.candyworld.client.model

import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.builders.LayerDefinition
import sokeriaaa.candyworld.CandyWorld

object ModModelLayers {

    val CANDY_SHEEP = create("candy_sheep")
    val CANDY_SHEEP_FLOSS = create("candy_sheep", "floss")
    val GUMMY_BEAR = create("gummy_bear")
    val GUMMY_MOUSE = create("gummy_mouse")
    val GUMMY_MOUSE_OUTER = create("gummy_mouse_outer")

    private fun create(name: String, model: String = "main"): ModelLayerLocation {
        return ModelLayerLocation(CandyWorld.id(name), model)
    }

    fun register(
        registerLayerDefinitions: (
            location: ModelLayerLocation,
            definition: () -> LayerDefinition,
        ) -> Unit
    ) {
        registerLayerDefinitions(CANDY_SHEEP) { CandySheepModel.createBodyLayer() }
        registerLayerDefinitions(CANDY_SHEEP_FLOSS) { CandySheepFlossModel.createBodyLayer() }
        registerLayerDefinitions(GUMMY_BEAR) { GummyBearModel.createBodyLayer() }
        registerLayerDefinitions(GUMMY_MOUSE) { GummyMouseModel.createBodyLayer() }
        registerLayerDefinitions(GUMMY_MOUSE_OUTER) { GummyMouseOuterModel.createBodyLayer() }
    }
}