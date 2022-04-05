package `fun`.picks.android_engineer_codecheck_sample.data.database

import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "created_unix_time") val createdUnixTime: Long
) {
    fun toMemo() = Memo(
        title = this.title,
        description = this.description,
        createdUnixTime = this.createdUnixTime
    )
}