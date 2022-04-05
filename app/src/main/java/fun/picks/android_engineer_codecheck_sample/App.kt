package `fun`.picks.android_engineer_codecheck_sample

import `fun`.picks.android_engineer_codecheck_sample.di.AppContainer
import android.app.Application

class App : Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}