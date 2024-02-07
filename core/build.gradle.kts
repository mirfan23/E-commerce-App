import com.android.build.api.dsl.BuildFeatures

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.core"
    compileSdk = 34
    buildFeatures.buildConfig = true

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"http://172.17.20.249:5000/\"")
        /**
         * another endpoint
         *
         * buildConfigField("String", "BASE_URL", "\"http://192.168.0.102:5000/\"")
         */
        buildConfigField("String", "API_KEY", "\"6f8856ed-9189-488f-9011-0ff4b6c08edc\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

    //retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:okhttp:4.9.0")
    api("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //chucker
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")

    //livedata
    implementation("android.arch.lifecycle:livedata:1.1.1")
    implementation("android.arch.lifecycle:livedata-core:1.1.1")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    //Data Store
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")

    //room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    //lifecycle
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //navigation
    api("androidx.navigation:navigation-fragment-ktx:2.3.5")
    api("androidx.navigation:navigation-ui-ktx:2.3.5")

    //koin
    api("io.insert-koin:koin-core:3.5.3")
    api("io.insert-koin:koin-android:3.5.3")

    //coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //paging
    implementation("androidx.paging:paging-runtime:3.0.0")

    //firebase
//    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
//    implementation("com.google.firebase:firebase-analytics")
//    implementation("com.google.firebase:firebase-auth")
//    implementation("com.google.firebase:firebase-firestore")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}