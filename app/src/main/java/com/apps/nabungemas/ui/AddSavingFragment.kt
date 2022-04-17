package com.apps.nabungemas.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.R
import com.apps.nabungemas.databinding.FragmentAddSavingBinding
import com.apps.nabungemas.viewmodel.TransactionViewModel
import com.apps.nabungemas.viewmodel.TransactionViewModelFactory


class AddSavingFragment : Fragment() {
    private val viewModel: TransactionViewModel by activityViewModels {
        TransactionViewModelFactory((activity?.application as DataApplication).database.transactionDao())
    }

    private var _binding: FragmentAddSavingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddSavingBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = resources.getStringArray(R.array.category_saving).toList()
        val adapter = ArrayAdapter(requireContext(), R.layout.list_dropdown, items)
        binding.tvCategory.setAdapter(adapter)
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnSave.setOnClickListener {
            addNewTransaction()
        }
    }

    private fun addNewTransaction() {
        if(isEntryValid()){
            viewModel.addNewSaving(
                binding.tvCategory.text.toString(),
                binding.etTarget.text.toString()
            )
            findNavController().navigateUp()
        }
    }

    private fun isEntryValid(): Boolean {
        return !(binding.tvCategory.text.toString().isBlank() ||
                binding.etTarget.text.toString().isBlank())

    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }

}