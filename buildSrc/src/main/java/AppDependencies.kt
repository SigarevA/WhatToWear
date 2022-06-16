object AppDependencies {
    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object UI {
        const val material = "com.google.android.material:material:${Versions.material}"

        const val compose = "androidx.compose.ui:ui:${Versions.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"

        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

        const val accompanistSwiperefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
        const val coliCompose = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Test {
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val androidJunit = "androidx.test.ext:junit:1.1.3"
        const val junit = "junit:junit:4.+"

        const val composeTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.serializationConverter}"
        const val kotlinxSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
        const val logger = "com.squareup.okhttp3:logging-interceptor:4.10.0"
    }

    object DB {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    }

    object DI {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

        const val hiltNavigation =
            "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    }

    object Navigation {
        const val composeDestinationsCore =
            "io.github.raamcosta.compose-destinations:core:${Versions.composeDestinations}"
        const val composeDestinationsKsp =
            "io.github.raamcosta.compose-destinations:ksp:${Versions.composeDestinations}"
    }

    object Yandex {
        const val maps = "com.yandex.android:maps.mobile:${Versions.yandexMaps}"
    }
}