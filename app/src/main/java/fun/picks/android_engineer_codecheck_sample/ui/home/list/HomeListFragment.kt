package `fun`.picks.android_engineer_codecheck_sample.ui.home.list

import `fun`.picks.android_engineer_codecheck_sample.App
import `fun`.picks.android_engineer_codecheck_sample.di.AppContainer
import `fun`.picks.android_engineer_codecheck_sample.di.HomeListContainer
import android.os.Bundle
import androidx.fragment.app.Fragment

class HomeListFragment : Fragment() {
    companion object {
        fun newInstance() = HomeListFragment()
    }

    private lateinit var appContainer: AppContainer
    private lateinit var viewModel: HomeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (requireActivity().application as App).appContainer
        appContainer.homeListContainer = HomeListContainer(appContainer.memoRepository).also {
            viewModel = it.homeListViewModelFactory.create()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.homeListContainer = null
    }
}