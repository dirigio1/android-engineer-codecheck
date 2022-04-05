package `fun`.picks.android_engineer_codecheck_sample.ui.home.list

import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import `fun`.picks.android_engineer_codecheck_sample.data.repository.MemoRepository
import `fun`.picks.android_engineer_codecheck_sample.ui.home.util.ViewModelFactory
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeListViewModel(
    private val memoRepository: MemoRepository
) : ViewModel() {
    private val _uiModelLD = MutableLiveData<HomeListUiModel>(HomeListUiModel.Loading)
    val uiModelLD: LiveData<HomeListUiModel>
        get() = _uiModelLD

    private val _errorLD = MutableLiveData<Int>()
    val errorLD: LiveData<Int>
        get() = _errorLD

    init { init() }

    @VisibleForTesting(otherwise = PRIVATE)
    fun init() {
        viewModelScope.launch {
            runCatching {
                _uiModelLD.value = HomeListUiModel.MemoItem(memoRepository.getAllMemo())
            }.onFailure {
                _errorLD.value = R.string.error_occurred
            }
        }
    }

    sealed class HomeListUiModel {
        data class MemoItem(val memos: List<Memo>) : HomeListUiModel()
        object Loading : HomeListUiModel()
    }

    class HomeListViewModelFactory(
        private val memoRepository: MemoRepository
    ) : ViewModelFactory<HomeListViewModel> {
        override fun create() = HomeListViewModel(memoRepository)
    }
}