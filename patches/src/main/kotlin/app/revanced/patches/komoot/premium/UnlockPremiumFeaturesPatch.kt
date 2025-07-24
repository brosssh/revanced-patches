package app.revanced.patches.komoot.premium

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.Opcode

@Suppress("unused")
val unlockProPatch = bytecodePatch(
    name = "Unlock Premium features",
    description = "Unlock Premium features. Some features are not possible to patch (server sided), " +
            "such as saving a multi-days hike."
) {
    compatibleWith("de.komoot.android"("2024.29.3"))

    execute {
        premiumConfigFingerprint.method.apply {
            val index = implementation!!.instructions.indexOfLast{
                Opcode.MOVE_OBJECT_FROM16 == it.opcode
            }

            addInstructions(
                index,
                """
                    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;
                    iput-object v1, v0, Lde/komoot/android/services/api/model/AppConfigV3;->v:Ljava/lang/Boolean;
                """
            )
        }

        displayPremiumFingerprint.method.apply {
            val index = implementation!!.instructions.indexOfLast{
                Opcode.INVOKE_DIRECT_RANGE == it.opcode
            }

            addInstructions(
                index,
                """
                    const/4 v0, 0x1
                    move v6, v0
                """
            )
        }

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
