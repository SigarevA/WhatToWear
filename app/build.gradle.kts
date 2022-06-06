import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "ru.sigarev.whattowear"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles += getDefaultProguardFile("proguard-android-optimize.txt")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta01"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {
    implementation(AppDependencies.AndroidX.coreKtx)
    implementation(AppDependencies.AndroidX.appcompat)
    implementation(AppDependencies.UI.material)

    implementation(AppDependencies.UI.compose)
    implementation(AppDependencies.UI.composeMaterial)
    implementation(AppDependencies.UI.composeTooling)

    implementation(AppDependencies.AndroidX.lifecycleRuntimeKtx)
    implementation(AppDependencies.AndroidX.activityCompose)
    testImplementation(AppDependencies.Test.junit)
    androidTestImplementation(AppDependencies.Test.androidJunit)
    androidTestImplementation(AppDependencies.Test.espresso)

    androidTestImplementation(AppDependencies.Test.composeTest)
    debugImplementation(AppDependencies.UI.tooling)

    implementation(AppDependencies.DI.hiltAndroid)
    kapt(AppDependencies.DI.hiltAndroidCompiler)

    implementation(AppDependencies.Network.retrofit)
    implementation(AppDependencies.Network.retrofitConverter)
    implementation(AppDependencies.Network.kotlinxSerialization)

    implementation(AppDependencies.Navigation.composeDestinationsCore)
    ksp(AppDependencies.Navigation.composeDestinationsKsp)

    implementation(AppDependencies.DB.roomRuntime)
    implementation(AppDependencies.DB.roomKtx)
    ksp(AppDependencies.DB.roomCompiler)

    implementation(AppDependencies.DI.hiltNavigation)
    implementation(AppDependencies.Yandex.maps)
}

kapt {
    correctErrorTypes = true
}