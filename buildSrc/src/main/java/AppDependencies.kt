object AppDependencies {
    private const val composeVersion = "1.2.0-beta02"

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
    }

    object UI {
        const val material = "com.google.android.material:material:1.6.0"

        const val compose = "androidx.compose.ui:ui:$composeVersion"
        const val composeMaterial = "androidx.compose.material:material:$composeVersion"
        const val composeTooling = "androidx.compose.ui:ui-tooling-preview:$composeVersion"

        const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    }

    object Test {
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val androidJunit = "androidx.test.ext:junit:1.1.3"
        const val junit = "junit:junit:4.+"

        const val composeTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val retrofitConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
        const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0"
    }

    object DB {
        private const val roomVersion = "2.4.2"

        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }

    object DI {
        const val hiltAndroid = "com.google.dagger:hilt-android:2.40"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:2.40"

        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Navigation {
        private const val version = "1.5.8-beta"

        const val composeDestinationsCore = "io.github.raamcosta.compose-destinations:core:$version"
        const val composeDestinationsKsp = "io.github.raamcosta.compose-destinations:ksp:$version"
    }

    object Yandex {
        const val maps = "com.yandex.android:maps.mobile:4.1.0-lite"
    }
}