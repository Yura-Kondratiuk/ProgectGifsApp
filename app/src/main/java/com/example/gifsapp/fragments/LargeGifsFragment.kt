package com.example.gifsapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.gifsapp.R
import com.example.gifsapp.adapters.LargeRecycleAdapter
import com.example.gifsapp.databinding.FragmentLargeGifsBinding
import com.example.gifsapp.utils.viewBinding
import com.example.gifsapp.viewModels.GifsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class LargeGifsFragment : Fragment(R.layout.fragment_large_gifs) {

    private val binding by viewBinding(FragmentLargeGifsBinding::bind)
    private val viewModel: GifsViewModel by sharedViewModel()
    private val args: LargeGifsFragmentArgs by navArgs()
    private var adapter: LargeRecycleAdapter = LargeRecycleAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        adapter.submitList(viewModel.gifs.value)
        binding.rvLargeGifs.scrollToPosition(args.position)
    }
    private fun initViews() {
        binding.rvLargeGifs.apply {
            layoutManager = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)
            adapter = this@LargeGifsFragment.adapter
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }
}