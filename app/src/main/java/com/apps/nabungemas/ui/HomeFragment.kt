package com.apps.nabungemas.ui

import android.content.ClipData
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import androidx.room.util.TableInfo
import com.apps.nabungemas.R
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.utils.Time
import com.apps.nabungemas.utils.Time.getTime
import com.apps.nabungemas.viewmodel.GoldViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class HomeFragment : Fragment() {
    //    private val viewModel: GoldViewModel by activityViewModels {
//        GoldViewModelFactory(
//            (activity?.application as DataApplication).database.transactionDao(),
//            requireActivity().application
//        )
//    }
    private var totalSaving = 0.0
    private var totalTarget = 0.0
    private var percentage: Double? = null

//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        _binding= FragmentHomeBinding.inflate(inflater,container,false)
//        val view = binding.root
//        return view
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme(darkTheme = false) {
                    HomeScreen()
                }
            }
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.fabAddItem.setOnClickListener {
//            view.findNavController().navigate(R.id.action_homeFragment_to_addTransactionFragment)
//        }
//
//
//
//        binding.tvSummaryTime.setText(getTime())
//
//         viewModel.allTotalSaving.observe(viewLifecycleOwner,{
//             if(it == null){
//                 totalSaving = 0.0
//                 binding.tvTotalSaving.text = context?.getString(R.string.total_saving,"0")
//             }else{
//                 totalSaving = it.toDouble()
//                 binding.tvTotalSaving.text = context?.getString(R.string.total_saving,it.toString())
//             }
//        })
//        viewModel.allTotalTarget.observe(viewLifecycleOwner,{
//            if(it == null){
//                totalTarget = 0.0
//                binding.tvTotalTarget.text = context?.getString(R.string.total_target,"0")
//                viewModel.getPercentage(totalSaving,totalTarget)
//            }else{
//                totalTarget = it.toDouble()
//                binding.tvTotalTarget.text = context?.getString(R.string.total_target,it.toString())
//                viewModel.getPercentage(totalSaving,totalTarget)
//            }
//
//        })
//        viewModel.percentage.observe(viewLifecycleOwner,{
//            binding.tvPercentage.text = context?.getString(R.string.percentage,it)
//        })
//        viewModel.goldCurrency.observe(viewLifecycleOwner,{
//            if(it == null){
//                binding.tvPricePergram.text = ""
//                binding.tvPricePrevious.text = ""
//                binding.tvPriceDifference.text = ""
//                binding.tvIdr.text = ""
//                binding.tvGoldTime.setText(getTime())
//                binding.tvKursTime.setText(getTime())
//            }else{
//                binding.tvPricePergram.text = context?.getString(R.string.price_pergram,it.priceGram24k)
//                binding.tvPricePrevious.text = context?.getString(R.string.price_previous,it.prevPrice)
//                binding.tvPriceDifference.text = context?.getString(R.string.price_double,it.priceDifferent)
//                binding.tvIdr.text = context?.getString(R.string.price_double,it.currency)
//                binding.tvGoldTime.setText(it.dateGold)
//                binding.tvKursTime.setText(it.dateCurrency)
//                if(it.priceDifferent <0){
//                    binding.tvPriceDifference.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_down, 0, 0, 0)
//                    binding.tvPriceDifference.getCompoundDrawables()[0].setTint(requireContext().getColor(R.color.red_500))
//                }else{
//                    binding.tvPriceDifference.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_up, 0, 0, 0)
//                    binding.tvPriceDifference.getCompoundDrawables()[0].setTint(requireContext().getColor(R.color.green_500))
//                }
//            }
//
//        })


//        transactionViewModel.savingnikah.observe(viewLifecycleOwner,{
//            binding.tvTotal.text = it.toString()
//        })

//    }

//    private fun getTime(): String? {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val timeNow = LocalDateTime.now()
//            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
//            val time = timeNow.format(formatter).toString()
//
//            return time
//
//        } else {
//            return null
//        }
//    }

    private fun percentage() {
        try {
            percentage = totalSaving.div(totalTarget).times(100)
        } catch (e: Exception) {
            percentage = 0.0
        }

    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }


}

@Composable
fun Header(modifier: Modifier,
           list:List<String>) {


    BoxWithConstraints(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(color = MaterialTheme.colors.primary)
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "Selamat Datang,",
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Selamat Menabung Emas",
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
        }
        Box(
            modifier
                .fillMaxWidth()
                .padding(top = 120.dp, start = 16.dp, end = 16.dp)
                .shadow(6.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color = Color.White)
                .padding(16.dp)
//                .wrapContentSize(Alignment.Center)

        ) {
            Row() {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.summary),
                        style = MaterialTheme.typography.h5,
                        color = Color.Gray
                    )
                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = stringResource(id = R.string.total_target, list[0]),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black
                    )
                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = stringResource(id = R.string.total_saving,list[1]),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black
                    )
                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = list[2],
                        style = MaterialTheme.typography.caption,
                        color = Color.Black
                    )
                }
                Text(modifier = modifier.fillMaxWidth(0.4f),
                    text = list[3],
                    color = Color.Gray)
            }
        }
    }

}

@Composable
fun GoldCard() {
    Box(
        Modifier
            .fillMaxWidth()

            .padding(
                top = 16.dp, start = 16.dp, end = 16.dp
            )
            .shadow(6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)
            .padding(16.dp)

//                .wrapContentSize(Alignment.Center)

    ) {


        Row(modifier = Modifier) {
            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.ic_about), contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.emas),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "14 April 2022",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Harga PerGram Rp. 900000",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Harga Terakhir Rp. 900000",
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Rp. 90000",
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )
            }

        }
    }
}

@Composable
fun CurrencyCard() {
    Box(
        Modifier
            .fillMaxWidth()

            .padding(16.dp)
            .shadow(6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)
            .padding(16.dp)

    ) {

        Row(modifier = Modifier) {
            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.ic_about), contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.forex),
                    style = MaterialTheme.typography.h5,
                    color = Color.Blue
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "14 April 2022",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.one_usd),
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Rp. 14000",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }

        }
    }
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: GoldViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val target by viewModel.allTargetState.collectAsState(initial = 0)
    val saving by viewModel.allSavingState.collectAsState(initial = 0)
    val time =getTime().toString()
    viewModel.getPercentage(saving?.toDouble(),target?.toDouble())
    val percentage by viewModel.percentState.collectAsState()
    val listHeader = listOf(target.toString(),saving.toString(),time,percentage)

    Column(modifier.background(color = Color(0xFFF4F9FB))) {
        Header(modifier = modifier, list = listHeader)
        Text(
            modifier = modifier.padding(top = 24.dp, start = 16.dp),
            text = stringResource(id = R.string.referensi_hari_ini),
            style = MaterialTheme.typography.h6,
            color = Color.Gray
        )
        GoldCard()
        CurrencyCard()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(darkTheme = false) {
        Column() {
            Header(modifier = Modifier,listOf("90000", "80000","19 januari 2023","40"))
            Text(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp),
                text = stringResource(id = R.string.referensi_hari_ini),
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
            GoldCard()
            CurrencyCard()
        }
    }
}