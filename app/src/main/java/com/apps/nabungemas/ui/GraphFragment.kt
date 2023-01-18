package com.apps.nabungemas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.apps.nabungemas.databinding.FragmentGraphBinding
import com.apps.nabungemas.network.GoldPriceApi
import kotlinx.coroutines.launch


class GraphFragment : Fragment() {
    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentGraphBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            val data = GoldPriceApi.retrofitService.getPrice()
            binding.text.text = data.toString()
        }

    }

}