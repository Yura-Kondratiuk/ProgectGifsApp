package com.example.gifsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gifsapp.R
import com.example.gifsapp.adapters.RecycleAdapter
import com.example.gifsapp.databinding.FragmentGifsBinding
import com.example.gifsapp.model.GifModel
import com.example.gifsapp.utils.viewBinding
import com.example.gifsapp.viewModels.GifsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class GifsFragment : Fragment(R.layout.fragment_gifs) {

    private val binding by viewBinding(FragmentGifsBinding::bind)
    private val viewModel: GifsViewModel by sharedViewModel()
    private var adapter: RecycleAdapter = RecycleAdapter(
        onClick = ::onGifClick,
        onLongClick = ::onLongClick
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGifs()
        observeData()
        initViews()
        initListeners()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gifs.collect{
                adapter.submitList(it)
            }
        }
    }

    private fun initViews() {
        binding.rvGifs.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = this@GifsFragment.adapter
        }
    }

    private fun initListeners() {
        binding.svGif.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.getGifs(p0.orEmpty())
                return true
            }
        })
    }

    private fun onGifClick(position: Int) {
        val action = GifsFragmentDirections.actionGifsFragmentToLargeGifsFragment(position)
        findNavController().navigate(action)
    }

    private fun onLongClick(id : String, position: Int) {
        viewModel.saveRemovedId(id)
        adapter.currentList.toMutableList().removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}
