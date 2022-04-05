package `fun`.picks.android_engineer_codecheck_sample.ui.home.list

import `fun`.picks.android_engineer_codecheck_sample.App
import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.databinding.FragmentHomeListBinding
import `fun`.picks.android_engineer_codecheck_sample.di.AppContainer
import `fun`.picks.android_engineer_codecheck_sample.di.HomeListContainer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class HomeListFragment : Fragment(R.layout.fragment_home_list) {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeListBinding.bind(view)

        observeErrorLD()
        observeUiModelLD(binding)
    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.homeListContainer = null
    }

    private fun observeErrorLD() {
        viewModel.errorLD.observe(viewLifecycleOwner) { stringRes ->
            AlertDialog.Builder(requireContext()).also {
                it.setMessage(requireContext().getString(stringRes))
                it.setPositiveButton(requireContext().getString(R.string.OK), null)
                it.show()
            }
        }
    }

    private fun observeUiModelLD(binding: FragmentHomeListBinding) {
        viewModel.uiModelLD.observe(viewLifecycleOwner) {

        }
    }
}