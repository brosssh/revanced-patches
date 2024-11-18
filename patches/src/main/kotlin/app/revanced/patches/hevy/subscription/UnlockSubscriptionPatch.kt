package app.revanced.patches.hevy.subscription

import app.revanced.patcher.patch.rawResourcePatch
import app.revanced.patches.shared.misc.hex.hexPatch
import app.revanced.patches.shared.misc.hex.Replacement

@Suppress("unused")
val unlockSubscriptionPatch = rawResourcePatch(
    name = "Unlock PRO",
    description = "Unlocks PRO Subscription.",
) {
    compatibleWith("com.hevy"("(2.0.0)"))

    execute {
        hexPatch{
            setOf(
                Replacement(
                    "66 38 66 66 37 37 30 31",
                    "66 38 37 66 37 37 30 31",
                    "assets/index.android.bundle"
                ),
                Replacement(
                    "30 66 38 66 66 35 63 30 31",
                    "30 66 38 37 66 35 63 30 31",
                    "assets/index.android.bundle"
                ),
                Replacement(
                    "66 38 66 66 30 64 30 33 30", //f8ff0d030, this will be replaced 3 times
                    "30 66 38 37 66 35 63 30 31",
                    "assets/index.android.bundle"
                ),
                Replacement(
                    "66 39 39 30 30 37 30 30 37 39 30 30 35 63",
                    "30 37 30 30 37 38 30 30 35 63",
                    "assets/index.android.bundle"
                )
            )
        }
    }
}