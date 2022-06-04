package com.kumar.loudly.test.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.now.test.presentation.util.setDivider
import com.kumar.loudly.test.R
import com.kumar.loudly.test.databinding.ActivityMainBinding
import com.kumar.loudly.test.domain.model.GithubRepositoryDomainModel
import com.kumar.loudly.test.presentation.ui.adapter.GitRepoAdapter
import com.kumar.loudly.test.presentation.viewmodel.GithubRepositoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GitRepoAdapter
    private val gitRepoViewModel: GithubRepositoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setupViews()
        showNoDataView()
    }

    private fun setupViews() {
        binding.searchView.isIconified = false
        binding.searchView.setOnQueryTextListener(this)

    }

    private fun setupObservers() {
        gitRepoViewModel.repoList.observe(this) {
            it.getContentIfNotHandled()?.let { repoList ->
                showDataView()
                setUpAdapter(repoList)
            }
        }
        gitRepoViewModel.noApi.observe(this) {
            it.getContentIfNotHandled()?.let { noData ->
                if (noData) {
                    showNoDataView()
                }
            }
        }
    }

    private fun showDataView() {
        binding.recyclerviewRepoList.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.textviewUserMessage.visibility = View.GONE
    }

    private fun showNoDataView() {
        binding.recyclerviewRepoList.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.textviewUserMessage.visibility = View.VISIBLE
        binding.textviewUserMessage.text = getString(R.string.user_message)
    }

    private fun showProgressBarView() {
        binding.recyclerviewRepoList.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        binding.textviewUserMessage.visibility = View.GONE
    }

    private fun setUpAdapter(repoList: List<GithubRepositoryDomainModel>) {
        val recyclerView: RecyclerView = binding.recyclerviewRepoList

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setDivider(R.drawable.recycler_view_divider)

        adapter = GitRepoAdapter(repoList) {
            // Do something on item click
        }

        recyclerView.adapter = adapter
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrBlank()) {
            gitRepoViewModel.onSearchApi(newText)
        }
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        showProgressBarView()
        gitRepoViewModel.onSearchApi(query)
        return false
    }
}