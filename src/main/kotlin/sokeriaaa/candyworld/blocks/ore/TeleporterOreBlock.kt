package sokeriaaa.candyworld.blocks.ore

import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.DropExperienceBlock

open class TeleporterOreBlock(properties: Properties) : DropExperienceBlock(UniformInt.of(3, 7), properties)