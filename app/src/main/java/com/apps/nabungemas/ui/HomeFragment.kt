package com.apps.nabungemas.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.R
import com.apps.nabungemas.viewmodel.GoldViewModel
import com.apps.nabungemas.databinding.FragmentHomeBinding
import com.apps.nabungemas.viewmodel.GoldViewModelFactory
import java.lang.Exception
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class HomeFragment : Fragment() {
    private val viewModel: GoldViewModel by activityViewModels {
        GoldViewModelFactory(
            (activity?.application as DataApplication).database.transactionDao(),requireActivity().application
        )
    }
    private var totalSaving=0.0
    private var totalTarget=0.0
    private var percentage:Double? = null

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddItem.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_addTransactionFragment)
        }



        binding.tvSummaryTime.setText(getTime())

         viewModel.allTotalSaving.observe(viewLifecycleOwner,{
             if(it == null){
                 totalSaving = 0.0
                 binding.tvTotalSaving.text = context?.getString(R.string.total_saving,"0")
             }else{
                 totalSaving = it.toDouble()
                 binding.tvTotalSaving.text = context?.getString(R.string.total_saving,it.toString())
             }
        })
        viewModel.allTotalTarget.observe(viewLifecycleOwner,{
            if(it == null){
                totalTarget = 0.0
                binding.tvTotalTarget.text = context?.getString(R.string.total_target,"0")
                viewModel.getPercentage(totalSaving,totalTarget)
            }else{
                totalTarget = it.toDouble()
                binding.tvTotalTarget.text = context?.getString(R.string.total_target,it.toString())
                viewModel.getPercentage(totalSaving,totalTarget)
            }

        })
        viewModel.percentage.observe(viewLifecycleOwner,{
            binding.tvPercentage.text = context?.getString(R.string.percentage,it)
        })
        viewModel.goldCurrency.observe(viewLifecycleOwner,{
            if(it == null){
                binding.tvPricePergram.text = ""
                binding.tvPricePrevious.text = ""
                binding.tvPriceDifference.text = ""
                binding.tvIdr.text = ""
                binding.tvGoldTime.setText(getTime())
                binding.tvKursTime.setText(getTime())
            }else{
                binding.tvPricePergram.text = context?.getString(R.string.price_pergram,it.priceGram24k)
                binding.tvPricePrevious.text = context?.getString(R.string.price_previous,it.prevPrice)
                binding.tvPriceDifference.text = context?.getString(R.string.price_double,it.priceDifferent)
                binding.tvIdr.text = context?.getString(R.string.price_double,it.currency)
                binding.tvGoldTime.setText(it.dateGold)
                binding.tvKursTime.setText(it.dateCurrency)
                if(it.priceDifferent <0){
                    binding.tvPriceDifference.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_down, 0, 0, 0)
                    binding.tvPriceDifference.getCompoundDrawables()[0].setTint(requireContext().getColor(R.color.red_500))
                }else{
                    binding.tvPriceDifference.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_up, 0, 0, 0)
                    binding.tvPriceDifference.getCompoundDrawables()[0].setTint(requireContext().getColor(R.color.green_500))
                }
            }

        })


//        transactionViewModel.savingnikah.observe(viewLifecycleOwner,{
//            binding.tvTotal.text = it.toString()
//        })

    }

    private fun getTime():String? {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val timeNow = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            val time = timeNow.format(formatter).toString()

             return time

        } else {
             return null
        }
    }

    private fun percentage(){
        try {
            percentage = totalSaving.div(totalTarget).times(100)
        }catch (e:Exception){
            percentage = 0.0
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}