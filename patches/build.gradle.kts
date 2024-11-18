group = "app.brosssh"

patches {
    about {
        name = "Brosssh fork of ReVanced Patched"
        description = "Brosssh fork of ReVanced Patched"
        source = "git@github.com:brosssh/revanced-patches.git"
        author = "Brosssh"
        contact = ""
        website = ""
        license = "GNU General Public License v3.0"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}