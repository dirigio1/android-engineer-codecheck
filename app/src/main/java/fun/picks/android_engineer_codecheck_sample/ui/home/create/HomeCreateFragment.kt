package `fun`.picks.android_engineer_codecheck_sample.ui.home.create

import `fun`.picks.android_engineer_codecheck_sample.App
import `fun`.picks.android_engineer_codecheck_sample.di.HomeCreateContainer
import android.os.Bundle
import androidx.fragment.app.Fragment

class HomeCreateFragment : Fragment() {
    companion object {
        fun newInstance() = HomeCreateFragment()
    }

    private val appContainer = (requireActivity().application as App).appContainer
    private lateinit var viewModel: HomeCreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer.homeCreateContainer = HomeCreateContainer(appContainer.memoRepository).also {
            viewModel = it.homeCreateViewModelFactory.create()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.homeCreateContainer = null
    }
}