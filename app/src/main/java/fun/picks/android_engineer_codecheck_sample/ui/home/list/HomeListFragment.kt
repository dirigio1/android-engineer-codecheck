package `fun`.picks.android_engineer_codecheck_sample.ui.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import `fun`.picks.android_engineer_codecheck_sample.R
import `fun`.picks.android_engineer_codecheck_sample.databinding.FragmentHomeListBinding
import `fun`.picks.android_engineer_codecheck_sample.databinding.ZHomeListItemCellBinding
import `fun`.picks.android_engineer_codecheck_sample.databinding.ZProgressLayoutBinding
import `fun`.picks.android_engineer_codecheck_sample.di.App
import `fun`.picks.android_engineer_codecheck_sample.di.AppContainer
import `fun`.picks.android_engineer_codecheck_sample.di.HomeListContainer
import `fun`.picks.android_engineer_codecheck_sample.ui.util.unixTimeToString
import java.lang.IllegalStateException

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
        val adapter = HomeListAdapter()

        setupRecyclerView(
            binding = binding,
            adapter = adapter
        )
        observeErrorLD()
        observeUiModelLD(adapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.homeListContainer = null
    }

    private fun setupRecyclerView(
        binding: FragmentHomeListBinding,
        adapter: HomeListAdapter
    ) {
        binding.recycler.adapter = adapter
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

    private fun observeUiModelLD(adapter: HomeListAdapter) {
        viewModel.uiModelLD.observe(viewLifecycleOwner) {
            adapter.submitList(it ?: return@observe)
        }
    }

    private class HomeListAdapter : ListAdapter<HomeListViewModel.HomeListUiModel, BaseHomeListViewHolder>(DiffUtilCallback()) {
        override fun getItemViewType(position: Int) = when (currentList[position]) {
            is HomeListViewModel.HomeListUiModel.Loading -> ViewType.PROGRESS.ordinal
            is HomeListViewModel.HomeListUiModel.MemoItem -> ViewType.ITEM.ordinal
            else -> throw IllegalStateException()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHomeListViewHolder = when (viewType) {
            ViewType.PROGRESS.ordinal -> HomeListProgressViewHolder.newInstance(parent)
            ViewType.ITEM.ordinal -> HomeListViewHolder.newInstance(parent)
            else -> throw IllegalStateException()
        }

        override fun onBindViewHolder(holder: BaseHomeListViewHolder, position: Int) {
            val item = currentList[position]

            when (holder) {
                is HomeListViewHolder -> holder.bind(item as HomeListViewModel.HomeListUiModel.MemoItem)
            }
        }

        private enum class ViewType {
            PROGRESS, ITEM
        }

        private class DiffUtilCallback : DiffUtil.ItemCallback<HomeListViewModel.HomeListUiModel>() {
            override fun areItemsTheSame(
                oldItem: HomeListViewModel.HomeListUiModel,
                newItem: HomeListViewModel.HomeListUiModel
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: HomeListViewModel.HomeListUiModel,
                newItem: HomeListViewModel.HomeListUiModel
            ) = oldItem == newItem
        }
    }

    private abstract class BaseHomeListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private class HomeListViewHolder(
        private val binding: ZHomeListItemCellBinding
    ) : BaseHomeListViewHolder(binding.root) {
        companion object {
            fun newInstance(parent: ViewGroup) = HomeListViewHolder(
                binding = ZHomeListItemCellBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        fun bind(item: HomeListViewModel.HomeListUiModel.MemoItem) {
            binding.titleText.text = item.memo.title
            binding.timeText.text = unixTimeToString(
                dateFormat = "yyyy/M/d H:m",
                unixTime = item.memo.createdUnixTime
            )
            binding.descriptionText.text = item.memo.description
        }
    }

    private class HomeListProgressViewHolder(
        private val binding: ZProgressLayoutBinding
    ) : BaseHomeListViewHolder(binding.root) {
        companion object {
            fun newInstance(parent: ViewGroup) = HomeListProgressViewHolder(
                binding = ZProgressLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}
