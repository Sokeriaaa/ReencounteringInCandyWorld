package sokeriaaa.candyworld.platform.neoforge.impl

import net.neoforged.fml.ModList
import sokeriaaa.candyworld.platform.common.CandyWorldCommon

class CandyWorldCommonImpl : CandyWorldCommon {
    override fun isModLoaded(id: String): Boolean {
        return ModList.get().isLoaded(id)
    }
}