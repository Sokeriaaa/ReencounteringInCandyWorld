package sokeriaaa.candyworld.configs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.KProperty

@Serializable
sealed interface Comment<T> {
    val comment: String?
    val value: T

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    @Serializable
    @SerialName("intValue")
    data class IntValue(
        override val comment: String?,
        override val value: Int,
    ) : Comment<Int>

    @Serializable
    @SerialName("boolValue")
    data class BoolValue(
        override val comment: String?,
        override val value: Boolean,
    ) : Comment<Boolean>

}