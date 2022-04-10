package `fun`.picks.android_engineer_codecheck_sample.di

import android.app.Application
import `fun`.picks.android_engineer_codecheck_sample.data.database.DatabaseProvider
import `fun`.picks.android_engineer_codecheck_sample.data.repository.MemoRepository
import `fun`.picks.android_engineer_codecheck_sample.data.repository.MemoRepositoryImpl
import `fun`.picks.android_engineer_codecheck_sample.ui.home.create.HomeCreateViewModel
import `fun`.picks.android_engineer_codecheck_sample.ui.home.list.HomeListViewModel

class AppContainer(application: Application) {
    private val databaseProvider = DatabaseProvider(application)
    val memoRepository: MemoRepository = MemoRepositoryImpl(databaseProvider)
    var homeCreateContainer: HomeCreateContainer? = null
    var homeListContainer: HomeListContainer? = null
}

class HomeCreateContainer(memoRepository: MemoRepository) {
    val homeCreateViewModelFactory = HomeCreateViewModel.HomeCreateViewModelFactory(memoRepository)
}

class HomeListContainer(memoRepository: MemoRepository) {
    val homeListViewModelFactory = HomeListViewModel.HomeListViewModelFactory(memoRepository)
}
