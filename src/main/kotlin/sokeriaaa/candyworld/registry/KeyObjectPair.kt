package sokeriaaa.candyworld.registry

import net.minecraft.resources.ResourceKey

class KeyObjectPair<T>(
    val path: String,
    private val lazy: Lazy<T>,
    val createKey: (String) -> ResourceKey<T>,
) {
    val key by lazy { createKey(path) }
    val value: T get() = lazy.value

    fun asRegistryObject(): RegistryObject<T> = RegistryObject(path, lazy)
}