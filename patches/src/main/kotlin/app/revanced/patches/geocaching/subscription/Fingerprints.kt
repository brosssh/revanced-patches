package app.revanced.patches.geocaching.subscription

import app.revanced.patcher.fingerprint

internal val userProfileDeserializerFingerprint = fingerprint {
    custom { methodDef, classDef ->
        classDef.endsWith("UserProfileResponse\$\$serializer;") && methodDef.name == "deserialize"
    }
}

internal val ownProfileDeserializerFingerprint = fingerprint {
    custom { methodDef, classDef ->
        classDef.endsWith("OwnProfileResponse\$Profile\$\$serializer;") && methodDef.name == "deserialize"
    }
}