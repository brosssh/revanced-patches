package app.revanced.patches.calistree.pro

import app.revanced.patcher.fingerprint

internal val proFingerprint = fingerprint {
    parameters("L", "L", "L")
    custom { methodDef, classDef ->
        classDef.endsWith("CustomerInfoFactory;") && methodDef.name == "buildCustomerInfo"
    }
}
