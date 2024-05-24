plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    `java-library`
    `maven-publish`
}

dependencies {
    implementation("org.tomlj:tomlj:1.1.1")
    implementation("com.github.bczhc:android-target-defs:ac1ea2f9fc")
    implementation(gradleApi())
    implementation("org.scala-lang:scala-library:2.13.10")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "pers.zhc"
            artifactId = "android-native-build-config-parser"
            version = "0.1"

            from(components["java"])
        }
    }
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("1.7.10")

            dependencies {
                // Use newer version of JUnit Engine for Kotlin Test
                implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
            }
        }
    }
}
