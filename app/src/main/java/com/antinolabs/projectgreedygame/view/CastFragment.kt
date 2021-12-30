package com.antinolabs.projectgreedygame.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.antinolabs.projectgreedygame.BaseFragment
import com.antinolabs.projectgreedygame.R
import com.antinolabs.projectgreedygame.databinding.FragmentCastBinding
import com.antinolabs.projectgreedygame.databinding.FragmentHomeBinding
import com.antinolabs.projectgreedygame.model.Cast
import com.antinolabs.projectgreedygame.model.SimilarMovie
import com.antinolabs.projectgreedygame.view.ViewModel.CastViewModel
import com.antinolabs.projectgreedygame.view.ViewModel.HomeViewModel
import com.antinolabs.projectgreedygame.view.adapter.CastXAdapter
import com.antinolabs.projectgreedygame.view.adapter.CrewAdapter

class CastFragment : BaseFragment<FragmentCastBinding, CastViewModel>() {

    override fun getFragmentView() = R.layout.fragment_cast

    override fun getViewModel() = CastViewModel::class.java

    private lateinit var castXAdapter: CastXAdapter
    private lateinit var crewAdapter: CrewAdapter

    override fun onResume() {
        super.onResume()
        val id=this.arguments?.getInt("id", -1) ?: -1
        if (id!=-1)
            viewModel.prodId=id

        castXAdapter=CastXAdapter()
        crewAdapter=CrewAdapter()
        binding.apply {
            btBack.setOnClickListener {
                Navigation.findNavController(root)
                    .popBackStack()
            }
            viewcastAdapter=castXAdapter
            viewcrewAdapter=crewAdapter


        }
        viewModel.CreditMovies().observe(viewLifecycleOwner, { data ->
            val res = data.data as Cast
            res.let {
                castXAdapter.submitList(it.cast)
                crewAdapter.submitList(it.crew)
            }
        })

    }
}