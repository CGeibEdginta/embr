plugins {
    id("embr.library-perspective-conventions")
}

dependencies {
    compileOnly(libs.bundles.common)
    compileOnly(libs.bundles.perspectiveCommon)
    compileOnly(projects.libraries.core.common)
}
