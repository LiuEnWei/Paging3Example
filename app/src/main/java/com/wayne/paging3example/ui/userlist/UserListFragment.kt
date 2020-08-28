package com.wayne.paging3example.ui.userlist

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.wayne.paging3example.R
import com.wayne.paging3example.base.BaseFragment
import com.wayne.paging3example.extensions.getRecyclerViewDivider
import com.wayne.paging3example.model.vo.User
import com.wayne.paging3example.ui.MainViewModel
import com.wayne.paging3example.ui.adapter.LoadMoreAdapter
import com.wayne.paging3example.ui.adapter.OnReloadListener
import com.wayne.paging3example.ui.adapter.OnUserClickListener
import com.wayne.paging3example.ui.adapter.UserAdapter
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment: BaseFragment(R.layout.fragment_user_list), OnUserClickListener,
        (CombinedLoadStates) -> Unit, OnReloadListener {
    private val viewModel by viewModels<UserListViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()
    private var userAdapter: UserAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.setLoading(true)

        userAdapter = UserAdapter(this)

        listUser.adapter = userAdapter?.withLoadStateFooter(LoadMoreAdapter(this))
        listUser.addItemDecoration(requireContext().getRecyclerViewDivider(R.drawable.divider_category))

        viewModel.userPaging.observe(viewLifecycleOwner, Observer {
            userAdapter?.submitData(lifecycle, it)
        })

        userAdapter?.addLoadStateListener(this)

        swipeRefresh.setOnRefreshListener {
            userAdapter?.refresh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userAdapter?.removeLoadStateListener(this)
    }

    override fun onClick(user: User) {
        findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(user))
    }

    override fun invoke(states: CombinedLoadStates) {
        // 下拉重載
        if (states.source.refresh is LoadState.NotLoading
            || states.source.refresh is LoadState.Error) {
            swipeRefresh.isRefreshing = false
        }

        // 列表最前面的資料加載完畢
        if (states.prepend.endOfPaginationReached) {
            mainViewModel.setLoading(false)
        }

        // 錯誤處理
        states.forEach { _, _, loadState ->
            if (loadState is LoadState.Error) {
                showErrorMessage(loadState.error.message,
                    DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                    })
                return
            }
        }
    }

    override fun onReloadClick() {
        userAdapter?.retry()
    }
}