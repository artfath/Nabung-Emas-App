package com.apps.nabungemas.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.R
import com.apps.nabungemas.databinding.FragmentAddTransactionBinding
import com.apps.nabungemas.viewmodel.TransactionViewModel
import com.apps.nabungemas.viewmodel.TransactionViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class AddTransactionFragment : Fragment() {
    private val viewModel:TransactionViewModel by activityViewModels {
        TransactionViewModelFactory((activity?.application as DataApplication).database.transactionDao())
    }


    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTransactionBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            addNewTransaction()
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val time = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

            binding.etDate.setText(time.format(formatter).toString(), TextView.BufferType.SPANNABLE)
        }

        val items = resources.getStringArray(R.array.category_saving).toList()
        val adapter = ArrayAdapter(requireContext(), R.layout.list_dropdown, items)
        binding.tvCategory.setAdapter(adapter)

        val products = resources.getStringArray(R.array.category_product).toList()
        val adapterProduct = ArrayAdapter(requireContext(), R.layout.list_dropdown, products)
        binding.tvProduct.setAdapter(adapterProduct)

        binding.btnDate.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

            datePicker.addOnPositiveButtonClickListener {
                // Respond to positive button click.
                val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.US)
                val date = dateFormatter.format(Date(it))
                binding.etDate.setText(date)
                Toast.makeText(context, "$date is selected", Toast.LENGTH_LONG).show()
            }
            datePicker.addOnNegativeButtonClickListener {
                // Respond to negative button click.
            }
            datePicker.addOnCancelListener {
                // Respond to cancel button click.
            }
            datePicker.addOnDismissListener {
                // Respond to dismiss events.
            }
            datePicker.show(parentFragmentManager,"tag")
        }
    }



    private fun addNewTransaction() {
        if(isEntryValid()){
            viewModel.addNewTransaction(
                binding.tvCategory.text.toString(),
                binding.etDate.text.toString(),
                binding.etPrice.text.toString(),
                binding.etQuantity.text.toString(),
                binding.tvProduct.text.toString()
            )
            findNavController().navigateUp()
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.tvCategory.text.toString(),
            binding.etDate.text.toString(),
            binding.etPrice.text.toString(),
            binding.etQuantity.text.toString(),
            binding.tvProduct.text.toString()
        )
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