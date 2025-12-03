package app.revanced.patches.flo.premium

import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.patches.flo.misc.extension.sharedExtensionPatch

private const val EXTENSION_CLASS_DESCRIPTOR =
    "Lapp/revanced/extension/flo/premium/UnlockPremiumPatch;"

@Suppress("unused")
val unlockPremiumPatch = bytecodePatch(
    name = "Unlock Premium features"
) {
    compatibleWith("org.iggymedia.periodtracker"("9.92.0"))

    dependsOn(sharedExtensionPatch)

    execute {
        userFeaturesDeserializerFingerprint.method.addInstruction(
            0,
            """
                invoke-static {}, $EXTENSION_CLASS_DESCRIPTOR->getPatchedFeatures()Ljava/util/Set;
                move-result-object v0
            """
        )
    }
}
