plugins {
    id("embr.kotlin-library-conventions")
    `java-library`
}

tasks.jar {
    archiveBaseName.set("embr-${project.parent?.name}-${project.name}")
}