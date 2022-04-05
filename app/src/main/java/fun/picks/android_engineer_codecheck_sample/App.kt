package `fun`.picks.android_engineer_codecheck_sample

import `fun`.picks.android_engineer_codecheck_sample.di.AppContainer
import android.app.Application

class App : Application() {
    val appContainer = AppContainer(this)
}