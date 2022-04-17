package com.apps.nabungemas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.R
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.databinding.FragmentSavingBinding
import com.apps.nabungemas.ui.adapter.SavingListAdapter
import com.apps.nabungemas.viewmodel.TransactionViewModel
import com.apps.nabungemas.viewmodel.TransactionViewModelFactory


class SavingFragment : Fragment() {
    private val viewModel: TransactionViewModel by activityViewModels {
        TransactionViewModelFactory(
            (activity?.application as DataApplication).database.transactionDao()
        )
    }
    private var _binding: FragmentSavingBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentSavingBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SavingListAdapter()
        viewModel.allSaving.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
//        viewModel.getSaving("Tabungan Rumah")
//        viewModel.saving.observe(viewLifecycleOwner,{
//            Log.e("data", it.toString())
//
//        })
//        viewModel.data.observe(viewLifecycleOwner,{
//            Log.d("dat table",it.toString())
//        })


        binding.apply {
            rvSaving.setHasFixedSize(true)
            rvSaving.adapter = adapter

        }
        topMenu()
    }

    private fun topMenu() {
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.add ->{
                    findNavController().navigate(R.id.action_savingFragment_to_addSavingFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}