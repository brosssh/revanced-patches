package app.revanced.patches.flo.misc.extension

import app.revanced.patches.flo.misc.extension.hooks.applicationInitHook
import app.revanced.patches.shared.misc.extension.sharedExtensionPatch

val sharedExtensionPatch = sharedExtensionPatch(
    "flo",
    applicationInitHook,
)
