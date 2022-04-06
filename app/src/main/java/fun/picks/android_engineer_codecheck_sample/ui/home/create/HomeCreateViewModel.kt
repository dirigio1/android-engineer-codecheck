package `fun`.picks.android_engineer_codecheck_sample.ui.home.create

import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import `fun`.picks.android_engineer_codecheck_sample.data.repository.MemoRepository
import `fun`.picks.android_engineer_codecheck_sample.ui.util.ViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeCreateViewModel(
    private val memoRepository: MemoRepository
) : ViewModel() {
    private val _errorLD = MutableLiveData<Int>()
    val errorLD: LiveData<Int>
        get() = _errorLD

    private val _titleErrorLD = MutableLiveData<Boolean>()
    val titleErrorLD: LiveData<Boolean>
        get() = _titleErrorLD

    private val _descriptionErrorLD = MutableLiveData<Boolean>()
    val descriptionErrorLD: LiveData<Boolean>
        get() = _descriptionErrorLD

    private val _createSuccessLD = MutableLiveData<Boolean>()
    val createSuccessLD: LiveData<Boolean>
        get() = _createSuccessLD

    fun onClickCreateButton(
        titleText: String,
        descriptionText: String
    ) {
        val isTitleBlank = titleText.isBlank()
        val isDescriptionBlank = descriptionText.isBlank()

        _titleErrorLD.value = isTitleBlank
        _descriptionErrorLD.value = isDescriptionBlank
        if (isTitleBlank || isDescriptionBlank) return

        viewModelScope.launch {
            runCatching {
                val nowUnix = System.currentTimeMillis() / 1000

                val memo = Memo(
                    title = titleText,
                    description = descriptionText,
                    createdUnixTime = nowUnix
                )

                memoRepository.insertAllMemo(memo)
                _createSuccessLD.value = true
            }.onFailure {
                _errorLD.value = R.string.error_occurred
            }
        }
    }

    class HomeCreateViewModelFactory(
        private val memoRepository: MemoRepository
    ) : ViewModelFactory<HomeCreateViewModel> {
        override fun create() = HomeCreateViewModel(memoRepository)
    }
}