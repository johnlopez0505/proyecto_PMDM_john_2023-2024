// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id ("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version  "2.50" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.1" apply false
}


buildscript {
    dependencies {
        classpath ("com.android.tools.build:gradle:4.1.3")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }

}