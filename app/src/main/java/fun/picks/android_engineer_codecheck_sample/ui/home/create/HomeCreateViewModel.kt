package `fun`.picks.android_engineer_codecheck_sample.ui.home.create

import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.data.model.Memo
import `fun`.picks.android_engineer_codecheck_sample.data.repository.MemoRepository
import `fun`.picks.android_engineer_codecheck_sample.ui.home.util.ViewModelFactory
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

    fun onClickCreateButton(
        titleText: String,
        descriptionText: String
    ) {
        _titleErrorLD.value = titleText.isBlank()
        _descriptionErrorLD.value = descriptionText.isBlank()

        viewModelScope.launch {
            runCatching {
                val nowUnix = System.currentTimeMillis() / 1000

                val memo = Memo(
                    title = titleText,
                    description = descriptionText,
                    createdUnixTime = nowUnix
                )

                memoRepository.insertAllMemo(memo)
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