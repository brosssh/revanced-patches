package app.revanced.patches.komoot.navigation

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal val routingPermissionFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR)
    parameters("L")
    custom { _, classDef ->
        classDef.endsWith("api/model/RoutingPermission;")
    }
}