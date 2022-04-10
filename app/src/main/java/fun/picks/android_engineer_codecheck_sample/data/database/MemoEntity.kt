package `fun`.picks.android_engineer_codecheck_sample.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "created_unix_time") val createdUnixTime: Long
) {
    fun toMemo() = Memo(
        title = this.title,
        description = this.description,
        createdUnixTime = this.createdUnixTime
    )
    companion object {
        fun turnIntoMemoEntity(memo: Memo) = MemoEntity(
            uid = 0,
            title = memo.title,
            description = memo.description,
            createdUnixTime = memo.createdUnixTime
        )
    }
}
