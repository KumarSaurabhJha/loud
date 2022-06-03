package com.kumar.loudly.test.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kumar.loudly.test.R
import com.kumar.loudly.test.domain.model.GithubRepositoryDomainModel

class GitRepoAdapter(
    private val repoList: List<GithubRepositoryDomainModel>,
    private val repoClickListener: (GithubRepositoryDomainModel) -> Unit
) : RecyclerView.Adapter<GitRepoAdapter.GitRepoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo_iten, parent, false)
        return GitRepoViewHolder(view)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: GitRepoViewHolder, position: Int) {
        val repo = repoList[position]

        holder.layout.setOnClickListener {
            repoClickListener(repo)
        }
    }

    class GitRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: ConstraintLayout = itemView.findViewById(R.id.repo_layout)
    }
}