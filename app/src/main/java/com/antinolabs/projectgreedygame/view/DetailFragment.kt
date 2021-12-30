package com.antinolabs.projectgreedygame.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.antinolabs.projectgreedygame.BaseFragment
import com.antinolabs.projectgreedygame.R
import com.antinolabs.projectgreedygame.databinding.FragmentCastBinding
import com.antinolabs.projectgreedygame.databinding.FragmentDetailBinding
import com.antinolabs.projectgreedygame.model.MovieDetail
import com.antinolabs.projectgreedygame.model.PopularMovie
import com.antinolabs.projectgreedygame.model.SimilarMovie
import com.antinolabs.projectgreedygame.view.ViewModel.CastViewModel
import com.antinolabs.projectgreedygame.view.ViewModel.DetailViewModel
import com.antinolabs.projectgreedygame.view.adapter.BrandListAdapter
import com.antinolabs.projectgreedygame.view.adapter.MovieListAdapter
import com.antinolabs.projectgreedygame.view.adapter.SimilarMovieAdapter

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override fun getFragmentView() = R.layout.fragment_detail

    override fun getViewModel() = DetailViewModel::class.java

    private lateinit var brandAdapter: BrandListAdapter
    private lateinit var similarMovieAdapter: SimilarMovieAdapter

    override fun onResume() {
        super.onResume()
        brandAdapter=BrandListAdapter()
        similarMovieAdapter= SimilarMovieAdapter()
        val id=this.arguments?.getInt("id", -1) ?: -1
        if (id!=-1)
            viewModel.prodId=id
        binding.apply {

            viewbrandAdapter=brandAdapter
            viewsimilarAdapter=similarMovieAdapter

            btBack.setOnClickListener {
                Navigation.findNavController(root)
                .popBackStack()
            }

            btCast.setOnClickListener {
                val bundle =Bundle()
                bundle.putInt("id",viewModel.prodId)
                Navigation.findNavController(root)
                    .navigate(R.id.action_detail_to_cast,bundle)
            }
            btReview.setOnClickListener {
                val bundle =Bundle()
                bundle.putInt("id",viewModel.prodId)
                Navigation.findNavController(root)
                    .navigate(R.id.action_detail_to_review,bundle) }
        }
        viewModel.productItembyid().observe(viewLifecycleOwner, { data ->
            val res = data.data as MovieDetail
            res.let {
                binding.viewdata=it
                brandAdapter.submitList(it.production_companies)
            }
        })

        viewModel.similarMovies().observe(viewLifecycleOwner, { data ->
            val res = data.data as SimilarMovie
            res.let {
                similarMovieAdapter.submitList(it.results)
            }
        })

    }

}