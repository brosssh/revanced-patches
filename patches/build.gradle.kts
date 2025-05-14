group = "app.brosssh"

patches {
    about {
        name = "Brosssh Patches"
        description = "Brosssh Patches"
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
