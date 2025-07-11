package app.revanced.patches.revenuecat.premium

import app.revanced.patcher.fingerprint

internal val proFingerprint = fingerprint {
    parameters("L", "L", "L")
    custom { methodDef, classDef ->
        classDef.endsWith("CustomerInfoFactory;") && methodDef.name == "buildCustomerInfo"
    }
}

internal val infoCacheOverwritePatch = fingerprint {
    custom { methodDef, classDef ->
        classDef.endsWith("CustomerInfoHelper;") && methodDef.name == "getCustomerInfoCachedOrFetched"
    }
}
