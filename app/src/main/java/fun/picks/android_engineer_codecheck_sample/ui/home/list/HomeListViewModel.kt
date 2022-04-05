package `fun`.picks.android_engineer_codecheck_sample.ui.home.list

import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import `fun`.picks.android_engineer_codecheck_sample.data.repository.MemoRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeListViewModel(
    private val memoRepository: MemoRepository
) : ViewModel() {
    private val _uiModelFlow = MutableStateFlow<HomeListUiModel>(HomeListUiModel.Loading)
    val uiModelFlow: StateFlow<HomeListUiModel>
        get() = _uiModelFlow

    sealed class HomeListUiModel {
        data class MemoItem(val memo: Memo) : HomeListUiModel()
        object Loading : HomeListUiModel()
    }
}