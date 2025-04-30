package app.revanced.patches.mapy.misc.spoof

import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.patch.bytecodePatch

@Suppress("unused")
val bypassSignatureCheck = bytecodePatch(
    name = "Bypass signature check",
) {
    compatibleWith("cz.seznam.mapy")

    execute {
        securityCheckFingerprint.method.addInstruction(0, "return-void")

    }
}
