package app.revanced.patches.revenuecat.premium

import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.patcher.patch.stringOption
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.builder.instruction.BuilderInstruction21t
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction
import com.android.tools.smali.dexlib2.iface.reference.StringReference

@Suppress("unused")
val unlockProPatch = bytecodePatch(
    name = "Unlock RevenueCat Pro",
)

{
    val entitlementsName by stringOption(
        key = "entitlements_name",
        default = "Premium",
        title = "Entitlement Name",
        description = "The name of the entitlement to unlock. Default is 'Premium'. This could be found in v1/product_entitlement_mapping or /offerings",
    )

    val subscriptionsName by stringOption(
        key = "subscriptions_name",
        default = "Premium",
        title = "Subscriptions Name",
        description = "The name of the subscriptions to unlock. Default is 'Premium'. This could be found in v1/product_entitlement_mapping or /offerings",
    )

    val product_identifier by stringOption(
        key = "product_identifier",
        default = "premium",
        title = "Product Identifier",
        description = "The product identifier for the subscription. Default is 'premium'. This could be found in v1/product_entitlement_mapping or /offerings",
    )

    val product_plan_identifier by stringOption(
        key = "product_plan_identifier",
        default = "",
        title = "Product Plan Identifier",
        description = "The product plan identifier for the subscription. Leave blank to omit this field.",
    )
    execute {
        proFingerprint.method.apply {
            val subscriptionsInstruction = implementation!!.instructions.first { it ->
                if (it.opcode != Opcode.CONST_STRING) return@first false
                ((it as ReferenceInstruction).reference as StringReference).string == "subscriptions"
            }

            val entitlementsInstruction = implementation!!.instructions.first { it ->
                if (it.opcode != Opcode.CONST_STRING) return@first false
                ((it as ReferenceInstruction).reference as StringReference).string == "entitlements"
            }

            val planField = if (product_plan_identifier.isNullOrBlank()) "" else ",\"product_plan_identifier\":\"$product_plan_identifier\""
            val subscriptionsJson = "{\"$product_identifier\":{\"auto_resume_date\":null,\"billing_issues_detected_at\":null,\"expires_date\":\"2099-12-31T00:00:00Z\",\"grace_period_expires_date\":null,\"is_sandbox\":false,\"original_purchase_date\":\"2000-01-01T00:00:00Z\",\"period_type\":\"normal\"$planField,\"purchase_date\":\"2000-01-01T00:00:00Z\",\"refunded_at\":null,\"store\":\"play_store\",\"unsubscribe_detected_at\":null}}"
            val entitlementsJson = "{\"$entitlementsName\":{\"expires_date\":\"2099-12-31T00:00:00Z\",\"grace_period_expires_date\":null,\"product_identifier\":\"$product_identifier\"$planField,\"purchase_date\":\"2000-01-01T00:00:00Z\"}}"

            addInstructions(
                subscriptionsInstruction.location.index + 3,
                """
                    const-string v4, "$subscriptionsJson"
                    new-instance v6, Lorg/json/JSONObject;
                    invoke-direct {v6, v4}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V
                    const-string v4, "subscriptions"
                """.trimIndent()
            )

            addInstructions(
                entitlementsInstruction.location.index + 3,
                """
                    const-string v9, "$entitlementsJson"
                    new-instance v8, Lorg/json/JSONObject;
                    invoke-direct {v8, v9}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V
                """.trimIndent()
            )
        }

        //The above patch is for some reason not being stored in cache
        //I'm therefore forcing the app to NOT get cached values but to get them from the API instead
        //The method that get from the API is the one patched above
        infoCacheOverwritePatch.method.apply {
            val instruction = implementation!!.instructions.first {
                Opcode.IF_EQZ == it.opcode
            }
            val register =  (instruction as BuilderInstruction21t).registerA
            addInstructions(
                instruction.location.index,
                "const/4 v$register, 0x0"
            )
        }
    }
}
