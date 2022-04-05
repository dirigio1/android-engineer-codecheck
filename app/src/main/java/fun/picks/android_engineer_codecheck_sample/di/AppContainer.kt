package `fun`.picks.android_engineer_codecheck_sample.di

import `fun`.picks.android_engineer_codecheck_sample.data.database.DatabaseProvider
import `fun`.picks.android_engineer_codecheck_sample.data.repository.MemoRepository
import `fun`.picks.android_engineer_codecheck_sample.data.repository.MemoRepositoryImpl
import `fun`.picks.android_engineer_codecheck_sample.ui.home.create.HomeCreateViewModel
import android.app.Application

class AppContainer(application: Application) {
    private val databaseProvider = DatabaseProvider(application)
    val memoRepository: MemoRepository = MemoRepositoryImpl(databaseProvider)
    var homeCreateContainer: HomeCreateContainer? = null
}

class HomeCreateContainer(memoRepository: MemoRepository) {
    val homeCreateViewModelFactory = HomeCreateViewModel.HomeCreateViewModelFactory(memoRepository)
}