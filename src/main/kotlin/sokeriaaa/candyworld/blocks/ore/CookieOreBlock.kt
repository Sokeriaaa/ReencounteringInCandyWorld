package sokeriaaa.candyworld.blocks.ore

import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.DropExperienceBlock

open class CookieOreBlock(properties: Properties) : DropExperienceBlock(UniformInt.of(0, 3), properties)