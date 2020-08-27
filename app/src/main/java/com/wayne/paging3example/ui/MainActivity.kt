package com.wayne.paging3example.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.wayne.paging3example.R
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appBarConfig = AppBarConfiguration(setOf(R.id.userListFragment))
        toolbar.setupWithNavController(findNavController(), appBarConfig)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(findNavController(), appBarConfig)

        viewModel.isLoading.observe(this, Observer {
            progress.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }
    }

    private fun findNavController(): NavController {
        return Navigation.findNavController(this, R.id.navHostFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}