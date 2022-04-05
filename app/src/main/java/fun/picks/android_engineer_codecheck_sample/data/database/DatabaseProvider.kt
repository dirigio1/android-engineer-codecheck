package `fun`.picks.android_engineer_codecheck_sample.data.database

import android.app.Application
import androidx.room.Room

class DatabaseProvider(
    application: Application
) {
    private val database = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "codecheck-sample-database"
    ).build()

    fun get() = database
}