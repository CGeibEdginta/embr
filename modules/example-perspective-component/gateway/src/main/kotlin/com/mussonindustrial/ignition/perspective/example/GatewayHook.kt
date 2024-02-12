package com.mussonindustrial.ignition.perspective.example

import com.inductiveautomation.ignition.common.licensing.LicenseState
import com.inductiveautomation.ignition.gateway.model.AbstractGatewayModuleHook
import com.inductiveautomation.ignition.gateway.model.GatewayContext
import com.inductiveautomation.perspective.common.api.ComponentRegistry
import com.inductiveautomation.perspective.gateway.api.ComponentModelDelegateRegistry
import com.inductiveautomation.perspective.gateway.api.PerspectiveContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*


@Suppress("unused")
class GatewayHook : AbstractGatewayModuleHook() {

    private val logger: Logger = LoggerFactory.getLogger("ExamplePerspectiveModule")
    private lateinit var gatewayContext: GatewayContext
    private lateinit var perspectiveContext: PerspectiveContext
    private lateinit var componentRegistry: ComponentRegistry
    private lateinit var modelDelegateRegistry: ComponentModelDelegateRegistry


    override fun setup(context: GatewayContext) {
        this.gatewayContext = context
    }

    override fun startup(activationState: LicenseState) {
        logger.info("Perspective example module started.")

        this.perspectiveContext = PerspectiveContext.get(this.gatewayContext)
        this.componentRegistry = perspectiveContext.componentRegistry
        this.modelDelegateRegistry = perspectiveContext.componentModelDelegateRegistry

        logger.info("Registering Components.")
        this.componentRegistry.registerComponent(SimpleComponent.DESCRIPTOR)

        logger.info("Registering model delegates.")
        this.modelDelegateRegistry.register(SimpleComponent.COMPONENT_ID, ::SimpleComponentModelDelegate)

    }

    override fun shutdown() {
        logger.info("Shutting down Component module and removing registered components.")
        this.componentRegistry.removeComponent(SimpleComponent.COMPONENT_ID)
        this.modelDelegateRegistry.remove(SimpleComponent.COMPONENT_ID)
    }

    override fun getMountedResourceFolder(): Optional<String>? {
        return Optional.of("web")
    }

    override fun getMountPathAlias(): Optional<String>? {
        return Optional.of(Components.URL_ALIAS)
    }

    override fun isFreeModule(): Boolean {
        return true
    }

    override fun isMakerEditionCompatible(): Boolean {
        return true
    }
}