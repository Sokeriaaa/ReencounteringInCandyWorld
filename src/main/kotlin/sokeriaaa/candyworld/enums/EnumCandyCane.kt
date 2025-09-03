package sokeriaaa.candyworld.enums

import net.minecraft.util.StringRepresentable

enum class EnumCandyCane(
    val meta: Int,
    val value: String,
) : StringRepresentable {
    WHITE(0, "white"),
    RED(1, "red"),
    GREEN(2, "green"),
    WHITE_RED(3, "white_red"),
    WHITE_GREEN(4, "white_green"),
    RED_GREEN(5, "red_green"),
    ;

    override fun getSerializedName(): String {
        return value
    }

    companion object {
        fun byMetadata(meta: Int): EnumCandyCane {
            return entries.getOrNull(meta) ?: entries.first()
        }
    }
}