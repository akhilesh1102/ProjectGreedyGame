package com.antinolabs.projectgreedygame.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.antinolabs.projectgreedygame.R
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_splash_to_home)
            }
            @Suppress("DEPRECATION")
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {

                requireActivity().window.insetsController?.show(WindowInsets.Type.statusBars())
            } else {
                requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }, 3000)
        return view
    }

}