package sokeriaaa.candyworld.enums

import net.minecraft.util.RandomSource
import net.minecraft.util.StringRepresentable
import kotlin.random.Random


enum class EnumGummy(
    val meta: Int,
    val value: String,
    val color: Int,
) : StringRepresentable {
    RED(0, "red", 0xff4530),
    ORANGE(1, "orange", 0xff9b4f),
    YELLOW(2, "yellow", 0xffe563),
    WHITE(3, "white", 0xfffeb0),
    GREEN(4, "green", 0x80e22b),
    ;

    override fun getSerializedName(): String {
        return value
    }

    companion object {
        val WORLDGEN_SEQUENCE = arrayOf<EnumGummy>(
            RED,
            ORANGE,
            YELLOW,
            GREEN,
            GREEN,
            YELLOW,
            WHITE,
            YELLOW,
            ORANGE,
            RED
        )

        fun byMetadata(meta: Int): EnumGummy {
            return entries.getOrNull(meta) ?: entries.first()
        }

        fun getGummyForGeneration(noise: Double): EnumGummy {
            var i: Int = (noise * 1.6).toInt() % WORLDGEN_SEQUENCE.size
            if (i < 0) i += WORLDGEN_SEQUENCE.size
            return WORLDGEN_SEQUENCE[i]
        }

        fun random(rand: Random): EnumGummy {
            return entries.random(rand)
        }

        fun random(rand: RandomSource): EnumGummy {
            return entries[rand.nextInt(entries.size)]
        }
    }
}