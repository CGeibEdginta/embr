package com.mussonindustrial.ignition.perspective.example

import com.inductiveautomation.ignition.common.licensing.LicenseState
import com.inductiveautomation.ignition.designer.model.AbstractDesignerModuleHook
import com.inductiveautomation.ignition.designer.model.DesignerContext
import com.inductiveautomation.perspective.designer.DesignerComponentRegistry
import com.inductiveautomation.perspective.designer.api.ComponentDesignDelegateRegistry
import com.inductiveautomation.perspective.designer.api.PerspectiveDesignerInterface
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Suppress("unused")
class DesignerHook : AbstractDesignerModuleHook() {

    private val logger: Logger = LoggerFactory.getLogger("ExamplePerspectiveModule")

    private lateinit var context: DesignerContext
    private lateinit var componentRegistry: DesignerComponentRegistry
    private lateinit var delegateRegistry: ComponentDesignDelegateRegistry


    override fun startup(context: DesignerContext, activationState: LicenseState) {
        logger.info("Perspective example module started.")
        this.context = context

        val pdi: PerspectiveDesignerInterface = PerspectiveDesignerInterface.get(context)

        componentRegistry = pdi.designerComponentRegistry
        delegateRegistry = pdi.componentDesignDelegateRegistry

        componentRegistry.registerComponent(SimpleComponent.DESCRIPTOR)
    }

    override fun shutdown() {
        logger.info("Shutting down Component module and removing registered components.")
        componentRegistry.removeComponent(SimpleComponent.COMPONENT_ID)
    }
}