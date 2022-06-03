package com.kumar.loudly.test.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.now.test.presentation.util.setDivider
import com.kumar.loudly.test.R
import com.kumar.loudly.test.databinding.ActivityMainBinding
import com.kumar.loudly.test.domain.model.GithubRepositoryDomainModel
import com.kumar.loudly.test.presentation.ui.adapter.GitRepoAdapter
import com.kumar.loudly.test.presentation.viewmodel.GithubRepositoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GitRepoAdapter
    private val gitRepoViewModel: GithubRepositoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gitRepoViewModel.init()
        setupObservers()
    }

    private fun setupObservers() {
        gitRepoViewModel.repoList.observe(this) {
            it.getContentIfNotHandled()?.let { repoList ->
                setUpAdapter(repoList)
            }
        }
    }

    private fun setUpAdapter(repoList: List<GithubRepositoryDomainModel>) {
        val recyclerView: RecyclerView = binding.recyclerviewRepoList

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setDivider(R.drawable.recycler_view_divider)

        adapter = GitRepoAdapter(repoList){
            gitRepoViewModel.onRepoClick(it)
        }

        recyclerView.adapter = adapter
    }
}