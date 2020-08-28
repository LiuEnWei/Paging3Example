package com.wayne.paging3example.ui.userdetail

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.wayne.paging3example.R
import com.wayne.paging3example.ui.MainViewModel
import com.wayne.paging3example.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_detail.*

class UserDetailFragment: BaseFragment(R.layout.fragment_user_detail) {
    private val viewModel by viewModels<UserDetailViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val user = UserDetailFragmentArgs.fromBundle(it).user
            Glide.with(imgAvatar)
                .load(user.avatarUrl)
                .placeholder(R.drawable.ic_avatar_placeholder)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(imgAvatar)
            textLogin.text = user.login
            textStaff.visibility = if (user.isSiteAdmin) {
                View.VISIBLE
            } else {
                View.GONE
            }

            mainViewModel.setLoading(true)
            viewModel.getUserDetail(user.login)
        }

        viewModel.userDetail.observe(viewLifecycleOwner, Observer {
            mainViewModel.setLoading(false)
            textName.text = it.name
            textBio.text = it.bio
            textLocation.text = it.location ?: getString(R.string.not_provided)
            textBlog.text = it.blog ?: getString(R.string.not_provided)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            mainViewModel.setLoading(false)
            showErrorMessage(it.message,
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
        })
    }
}