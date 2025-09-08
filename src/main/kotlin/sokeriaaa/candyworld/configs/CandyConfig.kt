package sokeriaaa.candyworld.configs

import kotlinx.serialization.Serializable
import sokeriaaa.candyworld.CandyWorld

@Serializable
sealed interface CandyConfig {

    @Serializable
    data class Common(
        val dimension: Dimension = Dimension(),
        val biome: Biome = Biome(),
        val mobs: Mobs = Mobs(),
        val general: General = General(),
    ) : CandyConfig {
        val disableTeleporter by dimension.disableTeleporter

        val weightCottonCandyPlains by biome.weightCottonCandyPlains
        val weightChocolateForest by biome.weightChocolateForest
        val weightGummySwamp by biome.weightGummySwamp

        val weightCottonCandySheep by mobs.weightCottonCandySheep
        val weightEasterChicken by mobs.weightEasterChicken
        val weightGummyMouse by mobs.weightGummyMouse
        val weightGummyBear by mobs.weightGummyBear
        val preventModdedMobSpawn by mobs.preventModdedMobSpawn

        val recursiveTreeTrunks by general.recursiveTreeTrunks
        val stackableTreeTrunks by general.stackableTreeTrunks

        @Serializable
        data class Dimension(
            val comment: String = "Dimension settings",
            val disableTeleporter: Comment.BoolValue = Comment.BoolValue(
                comment = "Setting this to true will prevent players from teleporting to the dimension",
                value = false,
            )
        )

        @Serializable
        data class Biome(
            val comment: String = "Biome settings",
            val weightCottonCandyPlains: Comment.IntValue = Comment.IntValue(
                comment = "Overworld cotton candy plains biome weight. Requires TerraBlender. 0 to prevent generation in overworld",
                value = 2,
            ),
            val weightChocolateForest: Comment.IntValue = Comment.IntValue(
                comment = "Overworld chocolate forest biome weight. Requires TerraBlender. 0 to prevent generation in overworld",
                value = 2,
            ),
            val weightGummySwamp: Comment.IntValue = Comment.IntValue(
                comment = "Overworld gummy swamp biome weight. Requires TerraBlender. 0 to prevent generation in overworld",
                value = 2,
            ),
        )

        @Serializable
        data class Mobs(
            val comment: String = "Mob settings",
            val weightCottonCandySheep: Comment.IntValue = Comment.IntValue(
                comment = "Cotton candy sheep weight. 0 to prevent spawning",
                value = 140,
            ),
            val weightEasterChicken: Comment.IntValue = Comment.IntValue(
                comment = "Easter chicken weight. 0 to prevent spawning",
                value = 140,
            ),
            val weightGummyMouse: Comment.IntValue = Comment.IntValue(
                comment = "Gummy mice weight. 0 to prevent spawning",
                value = 140,
            ),
            val weightGummyBear: Comment.IntValue = Comment.IntValue(
                comment = "Gummy bear weight. 0 to prevent spawning",
                value = 110,
            ),
            val preventModdedMobSpawn: Comment.BoolValue = Comment.BoolValue(
                comment = "Setting this to true should prevent any non-Candy World mobs from spawning in candy world biomes",
                value = false,
            ),
        )

        @Serializable
        data class General(
            val comment: String = "General settings",
            val recursiveTreeTrunks: Comment.BoolValue = Comment.BoolValue(
                comment = "Setting this to true will make tree trunks take longer to mine the higher they are",
                value = false,
            ),
            val stackableTreeTrunks: Comment.BoolValue = Comment.BoolValue(
                comment = "Setting this to false will make tree trunk blocks behave like normal blocks",
                value = true,
            )
        )

    }

    companion object {
        val COMMON: Common by lazy {
            CandyConfigHelper.createOrLoadConfig("${CandyWorld.MOD_ID}-common.json") { Common() }
        }
    }

}