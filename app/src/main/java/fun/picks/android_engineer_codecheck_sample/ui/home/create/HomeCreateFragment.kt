package `fun`.picks.android_engineer_codecheck_sample.ui.home.create

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.databinding.FragmentHomeCreateBinding
import `fun`.picks.android_engineer_codecheck_sample.di.App
import `fun`.picks.android_engineer_codecheck_sample.di.AppContainer
import `fun`.picks.android_engineer_codecheck_sample.di.HomeCreateContainer
import `fun`.picks.android_engineer_codecheck_sample.ui.home.ScreenTransition

class HomeCreateFragment : Fragment(R.layout.fragment_home_create) {
    companion object {
        fun newInstance() = HomeCreateFragment()
    }

    private lateinit var appContainer: AppContainer
    private lateinit var viewModel: HomeCreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (requireActivity().application as App).appContainer
        appContainer.homeCreateContainer = HomeCreateContainer(appContainer.memoRepository).also {
            viewModel = it.homeCreateViewModelFactory.create()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeCreateBinding.bind(view)

        setupButton(binding)
        observeTitleErrorLD(binding)
        observeDescriptionErrorLD(binding)
        observeErrorLD()
        observeCreateSuccessLD()
    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.homeCreateContainer = null
    }

    private fun setupButton(binding: FragmentHomeCreateBinding) {
        binding.createButton.setOnClickListener {
            viewModel.onClickCreateButton(
                titleText = binding.titleTextInputEditText.text.toString(),
                descriptionText = binding.descriptionTextInputEditText.text.toString()
            )
        }
    }

    private fun observeTitleErrorLD(binding: FragmentHomeCreateBinding) {
        viewModel.titleErrorLD.observe(viewLifecycleOwner) { errorOccurred ->
            when (errorOccurred) {
                true -> binding.titleTextInputLayout.error = requireContext().getString(R.string.please_input)
                else -> return@observe
            }
        }
    }

    private fun observeDescriptionErrorLD(binding: FragmentHomeCreateBinding) {
        viewModel.descriptionErrorLD.observe(viewLifecycleOwner) { errorOccurred ->
            when (errorOccurred) {
                true -> binding.descriptionTextInputLayout.error = requireContext().getString(R.string.please_input)
                else -> return@observe
            }
        }
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

    private fun observeCreateSuccessLD() {
        viewModel.createSuccessLD.observe(viewLifecycleOwner) {
            if (it == true) {
                (requireActivity() as? ScreenTransition)?.toHomeListFragment()
            }
        }
    }
}
