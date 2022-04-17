package com.apps.nabungemas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.R
import com.apps.nabungemas.databinding.FragmentTransactionBinding
import com.apps.nabungemas.ui.adapter.TransactionListAdapter
import com.apps.nabungemas.viewmodel.TransactionViewModel
import com.apps.nabungemas.viewmodel.TransactionViewModelFactory


class TransactionFragment : Fragment() {
    private val viewModel:TransactionViewModel by activityViewModels {
        TransactionViewModelFactory(
            (activity?.application as DataApplication).database.transactionDao()
        )
    }
    private var _binding:FragmentTransactionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TransactionListAdapter()


        viewModel.allTransaction.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
        binding.apply {
            rvTransaction.setHasFixedSize(true)
            rvTransaction.adapter = adapter
        }
        topMenu()

    }
    private fun topMenu() {
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.add ->{
                    findNavController().navigate(R.id.action_transactionFragment_to_addTransactionFragment)
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