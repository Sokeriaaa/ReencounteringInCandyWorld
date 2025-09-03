package sokeriaaa.candyworld.configs

import sokeriaaa.candyworld.helpers.JsonHelper
import sokeriaaa.candyworld.platform.Platform
import java.nio.file.Files

object CandyConfigHelper {
    inline fun <reified T : CandyConfig> createOrLoadConfig(
        path: String,
        default: () -> T,
    ): T {
        try {
            val configPath = Platform.config.getConfigPath().resolve(path)
            val config: T
            if (Files.notExists(configPath)) {
                Files.createDirectories(configPath.parent)
                Files.createFile(configPath)
                config = default()
                Files.writeString(configPath, JsonHelper.encode(config))
            } else {
                config = JsonHelper.decode(Files.readString(configPath))
            }
            return config
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}