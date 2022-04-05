package `fun`.picks.android_engineer_codecheck_sample.data.repository

import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo

interface MemoRepository {
    suspend fun getAllMemo(): List<Memo>
    suspend fun insertAllMemo(vararg memos: Memo)
}