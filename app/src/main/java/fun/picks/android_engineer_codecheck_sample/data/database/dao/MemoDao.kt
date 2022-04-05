package `fun`.picks.android_engineer_codecheck_sample.data.database.dao

import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll(): List<Memo>

    @Insert
    fun insertAll(vararg memos: Memo)
}