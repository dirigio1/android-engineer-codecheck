package `fun`.picks.android_engineer_codecheck_sample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MemoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}

