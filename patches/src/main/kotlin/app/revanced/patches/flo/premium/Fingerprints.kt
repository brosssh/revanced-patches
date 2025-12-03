package app.revanced.patches.flo.premium

import app.revanced.patcher.fingerprint

internal val userFeaturesDeserializerFingerprint = fingerprint {
    returns("Ljava/util/Set;")
    custom { _, classDef ->
        classDef.endsWith("PremiumValidationResponse;")
    }
}
