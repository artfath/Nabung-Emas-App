package com.apps.nabungemas.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.viewmodel.TransactionViewModel


//class SavingFragment : Fragment() {
//    private val viewModel: TransactionViewModel by activityViewModels {
//        TransactionViewModelFactory(
//            (activity?.application as DataApplication).database.transactionDao()
//        )
//    }
//    private var _binding: FragmentSavingBinding? = null
//
//    private val binding get() = _binding!!
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        _binding= FragmentSavingBinding.inflate(inflater,container,false)
////        val view = binding.root
////        return view
//        return ComposeView(requireContext()).apply {
//            setContent {
//                MyApplicationTheme(darkTheme = false) {
//                    SavingScreen()
//                }
//            }
//        }
//    }
//
//
//
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////        val adapter = SavingListAdapter()
////        viewModel.allSaving.observe(viewLifecycleOwner,{
////            adapter.submitList(it)
////        })
//////        viewModel.getSaving("Tabungan Rumah")
//////        viewModel.saving.observe(viewLifecycleOwner,{
//////            Log.e("data", it.toString())
//////
//////        })
//////        viewModel.data.observe(viewLifecycleOwner,{
//////            Log.d("dat table",it.toString())
//////        })
////
////
////        binding.apply {
////            rvSaving.setHasFixedSize(true)
////            rvSaving.adapter = adapter
////
////        }
////        topMenu()
////    }
////
////    private fun topMenu() {
////        binding.toolbar.setOnMenuItemClickListener {
////            when(it.itemId){
////                R.id.add ->{
////                    findNavController().navigate(R.id.action_savingFragment_to_addSavingFragment)
////                    true
////                }
////                else -> false
////            }
////        }
////    }
////
////    override fun onDestroyView() {
////        super.onDestroyView()
////        _binding = null
////    }
//
//
//}
@Composable
fun SavingScreen(navigateToAddSaving:()->Unit,
viewModel: TransactionViewModel=viewModel(factory = AppViewModelProvider.Factory)
) {
    val listSaving by viewModel.allSavingState.collectAsState(initial = null)
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Saving",
                version = 1,
                navigateAdd = navigateToAddSaving)
        },
        backgroundColor = Color(0xFFF4F9FB)
    )
    { innerPadding ->
        SavingBody(
            itemList = listSaving,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SavingBody(
    itemList: List<SavingTable>?,
    modifier: Modifier
) {
    Column() {
        if (itemList.isNullOrEmpty()) {
            Text(text = "No data")
        } else {
            SavingList(modifier = modifier,itemList)
        }
    }


}

@Composable
fun SavingList(modifier: Modifier,
               itemList: List<SavingTable>) {
    LazyColumn() {
        items(items = itemList) {
            SavingItem(modifier = modifier,it)
        }
    }
}

@Composable
fun SavingItem(modifier: Modifier,
               saving: SavingTable
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
            .shadow(6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)

    ) {


            Column(modifier = modifier) {
                Box(modifier = modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFDF5))) {
                    Text(modifier = modifier.padding(8.dp),
                        text = saving.savingCategory ?: "",
                        style = MaterialTheme.typography.h6)
                }

                Row(modifier = modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = modifier.weight(1f)) {
                        Text(
                            modifier = modifier,
                            text = stringResource(id = R.string.target,saving.target.toString()),
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            modifier = modifier,
                            text = stringResource(id = R.string.total_saving,
                                saving.totalSaving.toString()),
                            style = MaterialTheme.typography.body1
                        )
                    }


                    Text(
                        modifier = modifier.padding(start = 4.dp),
                        text = stringResource(id = R.string.percentage,
                            saving.percentage ?: 0.0),
                        style = MaterialTheme.typography.h6
                    )
                }
//                Row() {
//                    Text(
//                        modifier = modifier,
//                        text = transaction.product,
//                        style = MaterialTheme.typography.body1,
//                        color = Color(0xFFc8a600)
//                    )
//                    Spacer(modifier = modifier.weight(1f))
//                    Text(
//                        modifier = modifier,
//                        text = transaction.goldQuantity.toString(),
//                        style = MaterialTheme.typography.body2,
//                        color = Color(0xFF0081cb)
//                    )
//                }
            }
        }


}
@Preview(showBackground = true)
@Composable
fun SavingItemPreview(){
    SavingItem(modifier = Modifier,
        saving = SavingTable(savingCategory = "Tabungan Menikah", target = 9000000, totalSaving = 900000, percentage = 9.0))
}


@Preview(showBackground = true)
@Composable
fun SavingPreview() {
    MyApplicationTheme(darkTheme = false) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = "Saving",
                    version = 1,
                    navigateAdd = {})
            },
            backgroundColor = Color(0xFFF4F9FB)
        )
        { innerPadding ->
            SavingBody(
                itemList = listOf(SavingTable(savingCategory = "Tabungan Menikah", target = 9000000, totalSaving = 900000, percentage = 9.0),
                    SavingTable(savingCategory = "Tabungan Rumah", target = 9000000, totalSaving = 900000, percentage = 11.0)),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}