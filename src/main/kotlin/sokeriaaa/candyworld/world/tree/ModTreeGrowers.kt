package sokeriaaa.candyworld.world.tree

import net.minecraft.world.level.block.grower.TreeGrower
import sokeriaaa.candyworld.world.ModConfiguredAndPlaced
import java.util.*

object ModTreeGrowers {

    val CHOCOLATE: TreeGrower by lazy {
        TreeGrower(
            /* name = */ "chocolate_tree",
            /* megaTree = */ Optional.empty(),
            /* tree = */ Optional.of(ModConfiguredAndPlaced.CHOCOLATE.configured.key),
            /* flowers = */ Optional.empty()
        )
    }
    val COTTON_CANDY: TreeGrower by lazy {
        TreeGrower(
            /* name = */ "cotton_candy",
            /* megaTree = */ Optional.empty(),
            /* tree = */ Optional.of(ModConfiguredAndPlaced.COTTON_CANDY.configured.key),
            /* flowers = */ Optional.empty()
        )
    }

}