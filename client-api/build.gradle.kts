plugins {
    kotlin("multiplatform") version "1.6.10"
}

repositories {
    mavenCentral()
}



kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }

        dependencies {
            kotlin("stdlib")
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }


    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("com.rabbitmq:amqp-client:5.9.0")
                implementation("io.github.landgrafhomyak:cwapi:2!2020.01.27a0")
            }
        }
        val jvmTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}
