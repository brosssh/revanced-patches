package app.revanced.patches.komoot.navigation

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.Opcode

@Suppress("unused")
val unlockProPatch = bytecodePatch(
    name = "Unlock the routing and the maps download",
) {
    compatibleWith("de.komoot.android"("2024.29.3"))

    execute {
        routingPermissionFingerprint.method.apply {
            val index = implementation!!.instructions.indexOfLast{
                Opcode.MOVE_RESULT_OBJECT == it.opcode
            }

            addInstructions(
                index + 1,
                "sget-object p1, Lde/komoot/android/services/api/model/RoutingPermission\$StatusPermission;->GRANTED:Lde/komoot/android/services/api/model/RoutingPermission\$StatusPermission;",
            )
        }
    }
}