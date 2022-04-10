package `fun`.picks.android_engineer_codecheck_sample.di

import android.app.Application

class App : Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}
