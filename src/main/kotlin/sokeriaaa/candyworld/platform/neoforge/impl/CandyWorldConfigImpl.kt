package sokeriaaa.candyworld.platform.neoforge.impl

import net.neoforged.fml.loading.FMLPaths
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.platform.config.CandyWorldConfig
import java.nio.file.Path

class CandyWorldConfigImpl : CandyWorldConfig {
    override fun getConfigPath(): Path = FMLPaths.CONFIGDIR.get()
        .resolve(CandyWorld.MOD_ID)
}