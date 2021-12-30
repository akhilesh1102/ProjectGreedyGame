package com.antinolabs.projectgreedygame.view

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.antinolabs.projectgreedygame.BaseFragment
import com.antinolabs.projectgreedygame.R
import com.antinolabs.projectgreedygame.databinding.FragmentHomeBinding
import com.antinolabs.projectgreedygame.model.NowPlaying
import com.antinolabs.projectgreedygame.model.PopularMovie
import com.antinolabs.projectgreedygame.view.ViewModel.HomeViewModel
import com.antinolabs.projectgreedygame.view.adapter.MovieListAdapter
import com.antinolabs.projectgreedygame.view.adapter.SliderAdapter
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun getFragmentView() = R.layout.fragment_home

    override fun getViewModel() = HomeViewModel::class.java

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var swipeTimer: Timer


    override fun onResume() {
        super.onResume()
        movieListAdapter = MovieListAdapter()
        binding.apply {
            adapter = movieListAdapter
            recMovie.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (!recyclerView.canScrollVertically(1)) {
                            viewModel.popularMoviePage++
                            viewModel.productItembyid()
                        }
                    }
                }
            )

        }

        viewModel.productItembyid().observe(viewLifecycleOwner, { data ->
            val res = data.data as PopularMovie
            res.let {
                movieListAdapter.submitList(it.results)
            }
        })

        viewModel.NowPlaying().observe(viewLifecycleOwner, { data ->
            val res = data.data as NowPlaying
            res.let {
                pageSlider(res.results)
            }
        })


    }


    private fun pageSlider(list: List<NowPlaying.Result>) {


        val dotscount: Int
        val dots: Array<ImageView?>?
        var location = 0

        val viewPagerAdapter = SliderAdapter(list)

        binding.paggerSlider.clipToPadding = false
        binding.paggerSlider.offscreenPageLimit = 2
        binding.paggerSlider.setPadding(20, 0, 20, 0)
        binding.paggerSlider.pageMargin = 20

        binding.paggerSlider.adapter = viewPagerAdapter

        swipeTimer = Timer()

        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.paggerSlider.setCurrentItem(location + 1, true)
                    location++
                    if (viewPagerAdapter.count == location)
                        location = 0
                }, 0)
            }
        }, 3000, 3000)

        dotscount = viewPagerAdapter.count
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(requireContext())
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.non_active_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.SliderDots.addView(dots[i], params)
        }

        dots[0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.active_dot
            )
        )

        binding.paggerSlider.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                location = position
            }

            override fun onPageSelected(position: Int) {
                Log.d("data", "onPageSelected $position")
                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.non_active_dot
                        )
                    )
                }
                dots[position]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.active_dot
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


    }

}