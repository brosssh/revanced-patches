package app.revanced.patches.hevy.subscription

import app.revanced.patcher.patch.rawResourcePatch
import app.revanced.patches.shared.misc.hex.hexPatch
import app.revanced.patches.shared.misc.hex.Replacement

@Suppress("unused")
@Deprecated("This patch no longer works and will be removed in the future.")
val unlockSubscriptionPatch = rawResourcePatch(
    name = "Unlock PRO",
    description = "Unlocks PRO Subscription.",
) {
    compatibleWith("com.hevy"("2.0.0"))

    dependsOn (
        hexPatch{
            setOf(
                Replacement(
                    "f8 ff 77 01 7a",
                    "f8 7f 77 01 7a",
                    "assets/index.android.bundle"

                ),
                Replacement(
                    "00 f8 ff 5c 01",
                    "00 f8 7f 5c 01",
                    "assets/index.android.bundle"
                ),
                Replacement(
                    "f8 ff 0d 03 00",
                    "f8 7f 0d 03 00",
                    "assets/index.android.bundle"
                ),
                Replacement(
                    "f8 ff 0d 03 00",
                    "f8 7f 0d 03 00",
                    "assets/index.android.bundle"
                ),
                Replacement(
                    "f8 ff 0d 03 00",
                    "f8 7f 0d 03 00",
                    "assets/index.android.bundle"
                ),
                Replacement(
                    "f9 90 07 00 79 00 5c",
                    "f9 90 07 00 78 00 5c",
                    "assets/index.android.bundle"
                )
            )
        }
    )
}