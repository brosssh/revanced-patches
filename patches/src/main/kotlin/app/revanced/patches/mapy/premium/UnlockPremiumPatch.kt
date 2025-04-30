package app.revanced.patches.mapy.premium

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.util.indexOfFirstInstructionOrThrow
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

@Suppress("unused")
val bypassSignatureCheck = bytecodePatch(
    name = "Unlock premium features",
) {
    compatibleWith("cz.seznam.mapy")

    execute {
        userInfoFromJsonFingerprint.method.apply {
            val stringAssignInstructionIndex = userInfoFromJsonFingerprint.stringMatches!!.first().index

            val resultPremiumIndex = indexOfFirstInstructionOrThrow(
                stringAssignInstructionIndex,
                Opcode.MOVE_RESULT,
            )

            val premiumRegister = getInstruction<OneRegisterInstruction>(resultPremiumIndex).registerA

            addInstruction(resultPremiumIndex + 1,
                "const/16 v${premiumRegister}, 0x1")
        }

        featuresSyntheticInitFingerprint.method.addInstructions(0,
            """  
               const/4 p2, 0x1
               const/4 p3, 0x1
               const/4 p4, 0x1
               const/4 p5, -0x1
               const/4 p6, 0x1
               const/4 p7, 0x1
               const/4 p8, 0x1
            """
        )
    }
}
