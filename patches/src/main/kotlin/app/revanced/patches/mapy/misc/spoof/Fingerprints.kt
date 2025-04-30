package app.revanced.patches.mapy.misc.spoof

import app.revanced.patcher.fingerprint

internal val securityCheckFingerprint = fingerprint {
    custom { method, _ ->  method.name == "runSecurityChecks"}
}
