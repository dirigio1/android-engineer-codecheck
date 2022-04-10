package `fun`.picks.android_engineer_codecheck_sample.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll(): List<MemoEntity>

    @Insert
    fun insertAll(vararg memos: MemoEntity)
}
