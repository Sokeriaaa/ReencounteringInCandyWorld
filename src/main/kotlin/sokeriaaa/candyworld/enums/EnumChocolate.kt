package sokeriaaa.candyworld.enums

import net.minecraft.util.StringRepresentable

enum class EnumChocolate(
    val meta: Int,
    val value: String,
) : StringRepresentable {
    MILK(0, "milk"),
    WHITE(1, "white"),
    DARK(2, "dark"),
    ;

    override fun getSerializedName(): String {
        return value
    }
}