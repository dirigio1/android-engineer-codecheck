package `fun`.picks.android_engineer_codecheck_sample.data.database

import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}

