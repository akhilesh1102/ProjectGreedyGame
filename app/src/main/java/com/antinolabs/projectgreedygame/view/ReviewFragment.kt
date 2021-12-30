package com.antinolabs.projectgreedygame.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.antinolabs.projectgreedygame.BaseFragment
import com.antinolabs.projectgreedygame.R
import com.antinolabs.projectgreedygame.databinding.FragmentDetailBinding
import com.antinolabs.projectgreedygame.databinding.FragmentReviewBinding
import com.antinolabs.projectgreedygame.model.Cast
import com.antinolabs.projectgreedygame.model.Review
import com.antinolabs.projectgreedygame.view.ViewModel.DetailViewModel
import com.antinolabs.projectgreedygame.view.ViewModel.ReviewViewModel
import com.antinolabs.projectgreedygame.view.adapter.ReviewAdapter


class ReviewFragment : BaseFragment<FragmentReviewBinding, ReviewViewModel>() {

    override fun getFragmentView() = R.layout.fragment_review

    override fun getViewModel() = ReviewViewModel::class.java

    private lateinit var reviewAdapter: ReviewAdapter

    override fun onResume() {
        super.onResume()
        val id=this.arguments?.getInt("id", -1) ?: -1
        if (id!=-1)
            viewModel.prodId=id

        reviewAdapter=ReviewAdapter()
        binding.apply {
            viewreviewadapter = reviewAdapter
            btBack.setOnClickListener {
                Navigation.findNavController(root)
                    .popBackStack()
            }
        }

        viewModel.ReviewMovies().observe(viewLifecycleOwner, { data ->
            val res = data.data as Review
            res.let {
                reviewAdapter.submitList(it.results)
            }
        })
    }

}