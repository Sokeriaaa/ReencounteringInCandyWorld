package sokeriaaa.candyworld.platform

import sokeriaaa.candyworld.platform.common.CandyWorldCommon
import sokeriaaa.candyworld.platform.config.CandyWorldConfig
import sokeriaaa.candyworld.platform.entity.CandyWorldEntity
import sokeriaaa.candyworld.platform.fluid.CandyWorldFluid
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import java.util.*

object Platform {

    @PublishedApi
    internal val common: CandyWorldCommon by lazy {
        ServiceLoader.load(CandyWorldCommon::class.java).first()
    }

    @PublishedApi
    internal val config: CandyWorldConfig by lazy {
        ServiceLoader.load(CandyWorldConfig::class.java).first()
    }

    @PublishedApi
    internal val entity: CandyWorldEntity by lazy {
        ServiceLoader.load(CandyWorldEntity::class.java).first()
    }

    @PublishedApi
    internal val fluid: CandyWorldFluid by lazy {
        ServiceLoader.load(CandyWorldFluid::class.java).first()
    }

    @PublishedApi
    internal val registry: CandyWorldRegistry by lazy {
        ServiceLoader.load(CandyWorldRegistry::class.java).first()
    }

}
