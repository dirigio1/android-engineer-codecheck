package `fun`.picks.android_engineer_codecheck_sample.data.repository

import `fun`.picks.android_engineer_codecheck_sample.data.database.DatabaseProvider
import `fun`.picks.android_engineer_codecheck_sample.data.database.MemoEntity.Companion.turnIntoMemoEntity
import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemoRepositoryImpl(databaseProvider: DatabaseProvider) : MemoRepository {
    private val database = databaseProvider.get()

    override suspend fun getAllMemo(): List<Memo> = withContext(Dispatchers.IO) {
        database.memoDao().getAll().map { it.toMemo() }
    }

    override suspend fun insertAllMemo(vararg memos: Memo) = withContext(Dispatchers.IO) {
        val entityArray = memos.map { turnIntoMemoEntity(it) }.toTypedArray()
        database.memoDao().insertAll(memos = entityArray)
    }
}
