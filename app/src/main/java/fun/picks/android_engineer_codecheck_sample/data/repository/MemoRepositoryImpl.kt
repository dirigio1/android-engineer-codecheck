package `fun`.picks.android_engineer_codecheck_sample.data.repository

import `fun`.picks.android_engineer_codecheck_sample.data.database.AppDatabase
import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemoRepositoryImpl(
    private val database: AppDatabase
) : MemoRepository {
    override suspend fun getAllMemo(): List<Memo> = withContext(Dispatchers.IO) {
        database.memoDao().getAll()
    }

    override suspend fun insertAllMemo(vararg memos: Memo) = withContext(Dispatchers.IO) {
        database.memoDao().insertAll(memos = memos)
    }
}