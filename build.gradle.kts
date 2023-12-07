plugins {
    kotlin("jvm") version "1.9.20"
}
dependencies {
    testImplementation("junit:junit:4.13.2")
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
