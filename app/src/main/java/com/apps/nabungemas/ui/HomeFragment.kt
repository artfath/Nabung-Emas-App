package com.apps.nabungemas.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.nabungemas.R
import com.apps.nabungemas.data.GoldCurrencyTable
import com.apps.nabungemas.ui.navigation.NavigationDestination
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.utils.Time.getTime
import com.apps.nabungemas.viewmodel.GoldViewModel


object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val title: Int = R.string.home

}
//class HomeFragment : Fragment() {
//    //    private val viewModel: GoldViewModel by activityViewModels {
////        GoldViewModelFactory(
////            (activity?.application as DataApplication).database.transactionDao(),
////            requireActivity().application
////        )
////    }
//    private var totalSaving = 0.0
//    private var totalTarget = 0.0
//    private var percentage: Double? = null
//
////    private var _binding: FragmentHomeBinding? = null
////    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        _binding= FragmentHomeBinding.inflate(inflater,container,false)
////        val view = binding.root
////        return view
//        return ComposeView(requireContext()).apply {
//            setContent {
//                MyApplicationTheme(darkTheme = false) {
//                    HomeScreen()
//                }
//            }
//        }
//    }
//
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////        binding.fabAddItem.setOnClickListener {
////            view.findNavController().navigate(R.id.action_homeFragment_to_addTransactionFragment)
////        }
////
////
////
////        binding.tvSummaryTime.setText(getTime())
////
////         viewModel.allTotalSaving.observe(viewLifecycleOwner,{
////             if(it == null){
////                 totalSaving = 0.0
////                 binding.tvTotalSaving.text = context?.getString(R.string.total_saving,"0")
////             }else{
////                 totalSaving = it.toDouble()
////                 binding.tvTotalSaving.text = context?.getString(R.string.total_saving,it.toString())
////             }
////        })
////        viewModel.allTotalTarget.observe(viewLifecycleOwner,{
////            if(it == null){
////                totalTarget = 0.0
////                binding.tvTotalTarget.text = context?.getString(R.string.total_target,"0")
////                viewModel.getPercentage(totalSaving,totalTarget)
////            }else{
////                totalTarget = it.toDouble()
////                binding.tvTotalTarget.text = context?.getString(R.string.total_target,it.toString())
////                viewModel.getPercentage(totalSaving,totalTarget)
////            }
////
////        })
////        viewModel.percentage.observe(viewLifecycleOwner,{
////            binding.tvPercentage.text = context?.getString(R.string.percentage,it)
////        })
////        viewModel.goldCurrency.observe(viewLifecycleOwner,{
////            if(it == null){
////                binding.tvPricePergram.text = ""
////                binding.tvPricePrevious.text = ""
////                binding.tvPriceDifference.text = ""
////                binding.tvIdr.text = ""
////                binding.tvGoldTime.setText(getTime())
////                binding.tvKursTime.setText(getTime())
////            }else{
////                binding.tvPricePergram.text = context?.getString(R.string.price_pergram,it.priceGram24k)
////                binding.tvPricePrevious.text = context?.getString(R.string.price_previous,it.prevPrice)
////                binding.tvPriceDifference.text = context?.getString(R.string.price_double,it.priceDifferent)
////                binding.tvIdr.text = context?.getString(R.string.price_double,it.currency)
////                binding.tvGoldTime.setText(it.dateGold)
////                binding.tvKursTime.setText(it.dateCurrency)
////                if(it.priceDifferent <0){
////                    binding.tvPriceDifference.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_down, 0, 0, 0)
////                    binding.tvPriceDifference.getCompoundDrawables()[0].setTint(requireContext().getColor(R.color.red_500))
////                }else{
////                    binding.tvPriceDifference.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_up, 0, 0, 0)
////                    binding.tvPriceDifference.getCompoundDrawables()[0].setTint(requireContext().getColor(R.color.green_500))
////                }
////            }
////
////        })
//
//
////        transactionViewModel.savingnikah.observe(viewLifecycleOwner,{
////            binding.tvTotal.text = it.toString()
////        })
//
////    }
//
////    private fun getTime(): String? {
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            val timeNow = LocalDateTime.now()
////            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
////            val time = timeNow.format(formatter).toString()
////
////            return time
////
////        } else {
////            return null
////        }
////    }
//
//    private fun percentage() {
//        try {
//            percentage = totalSaving.div(totalTarget).times(100)
//        } catch (e: Exception) {
//            percentage = 0.0
//        }
//
//    }
//
//
////    override fun onDestroyView() {
////        super.onDestroyView()
////        _binding = null
////    }
//
//
//}


@Composable
fun HomeScreen(
    navigateToAddTransaction: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GoldViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val target by viewModel.allTargetState.collectAsState(initial = 0)
    val saving by viewModel.allSavingState.collectAsState(initial = 0)
    viewModel.getPercentage(saving?.toDouble(), target?.toDouble())
    val percentage by viewModel.percentState.collectAsState()
    val listHeader = listOf(target.toString(), saving.toString(), percentage)
    val goldCurrency by viewModel.goldCurrencyState.collectAsState(initial = null)
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddTransaction,
                modifier = modifier.navigationBarsPadding(),
                backgroundColor = colorResource(id = R.color.blue_500)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add), contentDescription = "",
                    tint = Color.White
                )
            }
        }) { innerPadding ->
        HomeBody(
            modifier = modifier.padding(innerPadding),
            listHeader = listHeader,
            goldCurrency = goldCurrency
        )

    }


}

@Composable
fun HomeBody(
    modifier: Modifier,
    listHeader: List<String>,
    goldCurrency: GoldCurrencyTable?
) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.grey_100))
    ) {
        Header(
            modifier = modifier,
            list = listHeader
        )
        Text(
            modifier = modifier.padding(top = 24.dp, start = 16.dp),
            text = stringResource(id = R.string.referensi_hari_ini),
            style = MaterialTheme.typography.h6,
            color = Color.Gray
        )
        GoldCard(goldCurrency = goldCurrency)
        CurrencyCard(date = goldCurrency?.dateCurrency, currency = goldCurrency?.currency)
    }
}

@Composable
fun Header(
    modifier: Modifier,
    list: List<String>
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(color = colorResource(id = R.color.yellow_200))
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
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.summary),
                        style = MaterialTheme.typography.h5,
                        color = colorResource(id = R.color.grey_700)
                    )
                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = stringResource(id = R.string.total_target, list[0]),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black
                    )
                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = stringResource(id = R.string.total_saving, list[1]),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black
                    )
                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = getTime() ?: "",
                        style = MaterialTheme.typography.caption,
                        color = Color.Black
                    )
                }
                Box(modifier = modifier.weight(0.4f)) {
                    Text(
                        modifier = modifier,
                        text = list[2],
                        style = MaterialTheme.typography.h5,
                        color = Color.Black
                    )
                }

            }
        }
    }

}

@Composable
fun GoldCard(goldCurrency: GoldCurrencyTable?) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
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
                    text = stringResource(id = R.string.emas),
                    style = MaterialTheme.typography.h5,
                    color = colorResource(id = R.color.yellow_700)
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = goldCurrency?.dateGold ?: getTime().toString(),
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(
                        id = R.string.price_pergram,
                        goldCurrency?.priceGram24k ?: 0.0
                    ),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(
                        id = R.string.price_previous,
                        goldCurrency?.prevPrice ?: 0.0
                    ),
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(
                        id = R.string.price_double,
                        goldCurrency?.priceDifferent ?: 0.0
                    ),
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )
            }

        }
    }
}

@Composable
fun CurrencyCard(
    date:String?,
    currency:Double?
) {
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
                painter = painterResource(id = R.drawable.ic_about),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.forex),
                    style = MaterialTheme.typography.h5,
                    color = colorResource(id = R.color.blue_700)
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = date ?: getTime().toString(),
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
                    text = stringResource(
                        id = R.string.price_double,
                        currency ?: 0.0),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MyApplicationTheme(darkTheme = false) {
        Column() {
            Header(modifier = Modifier, listOf("90000", "80000", "40 %"))
            Text(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp),
                text = stringResource(id = R.string.referensi_hari_ini),
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
            GoldCard(
                goldCurrency = GoldCurrencyTable(
                    0,
                    15000.0,
                    899000.0,
                    1000.0,
                    900000.0,
                    "22 januari 2023",
                    "22 januari 2023"
                )
            )
            CurrencyCard(
                date = "22 januari 2023",
                currency = 15000.0
            )
        }
    }
}