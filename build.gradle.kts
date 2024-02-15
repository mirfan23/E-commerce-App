// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("io.gitlab.arturbosch.detekt") version("1.23.3")
    id("com.android.library") version "8.2.0" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
}