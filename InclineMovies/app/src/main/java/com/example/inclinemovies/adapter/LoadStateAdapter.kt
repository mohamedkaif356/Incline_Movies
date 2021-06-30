package com.example.inclinemovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inclinemovies.R

class LoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<com.example.inclinemovies.adapter.LoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_load_state, parent, false))
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val progressBar = itemView.findViewById<ProgressBar>(R.id.pb_load)
        private val buttonRetry = itemView.findViewById<Button>(R.id.btn_retry)
        private val textViewError = itemView.findViewById<TextView>(R.id.tv_error)

        init {
            buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            buttonRetry.isVisible = loadState !is LoadState.Loading
            textViewError.isVisible = loadState !is LoadState.Loading

        }

    }


}