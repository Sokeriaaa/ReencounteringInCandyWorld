package sokeriaaa.candyworld.registry

data class RegistryObject<T>(
    val path: String,
    private val lazy: Lazy<T>,
) {
    val value: T get() = lazy.value
}