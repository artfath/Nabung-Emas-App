package com.apps.nabungemas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.databinding.FragmentSavingBinding
import com.apps.nabungemas.ui.adapter.SavingListAdapter
import com.apps.nabungemas.ui.theme.MyApplicationTheme
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
//        _binding= FragmentSavingBinding.inflate(inflater,container,false)
//        val view = binding.root
//        return view
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme(darkTheme = false) {
                    SavingScreen()
                }
            }
        }
    }



//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val adapter = SavingListAdapter()
//        viewModel.allSaving.observe(viewLifecycleOwner,{
//            adapter.submitList(it)
//        })
////        viewModel.getSaving("Tabungan Rumah")
////        viewModel.saving.observe(viewLifecycleOwner,{
////            Log.e("data", it.toString())
////
////        })
////        viewModel.data.observe(viewLifecycleOwner,{
////            Log.d("dat table",it.toString())
////        })
//
//
//        binding.apply {
//            rvSaving.setHasFixedSize(true)
//            rvSaving.adapter = adapter
//
//        }
//        topMenu()
//    }
//
//    private fun topMenu() {
//        binding.toolbar.setOnMenuItemClickListener {
//            when(it.itemId){
//                R.id.add ->{
//                    findNavController().navigate(R.id.action_savingFragment_to_addSavingFragment)
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }


}
@Composable
fun SavingBody(
    itemList: List<TransactionTable>,
    modifier: Modifier
) {
    Column() {
        if (itemList.isEmpty()) {
            Text(text = "No data")
        } else {
            SavingList(modifier = modifier,itemList)
        }
    }


}

@Composable
fun SavingList(modifier: Modifier, itemList: List<TransactionTable>) {
    LazyColumn() {
        items(items = itemList) {
            SavingItem(modifier = modifier,it)
        }
    }
}

@Composable
fun SavingItem(modifier: Modifier,
               transaction: TransactionTable
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
                        text = "Tabungan",
                        style = MaterialTheme.typography.h6)
                }

                Row(modifier = modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = modifier.weight(1f)) {
                        Text(
                            modifier = modifier,
                            text = "Target Rp 2000000",
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            modifier = modifier,
                            text = "tabungan Rp 200000",
                            style = MaterialTheme.typography.body1
                        )
                    }


                    Text(
                        modifier = modifier.padding(start = 4.dp),
                        text = "90%",
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
    SavingItem(modifier = Modifier, transaction = TransactionTable(0,"20 januari 2023","tabungan menikah",20000,1.0,"antam"))
}
@Composable
fun SavingScreen() {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Saving",
                version = 1,
                navigateUp = {})
        },
        backgroundColor = Color(0xFFF4F9FB)
    )
    { innerPadding ->
        SavingBody(
            itemList = listOf(
                TransactionTable(0,"20 januari 2023","tabungan menikah",20000,1.0,"antam"),
                TransactionTable(1,"20 januari 2023","tabungan menikah",20000,1.0,"antam")
            ),
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SavingPreview() {
    MyApplicationTheme(darkTheme = false) {
        SavingScreen()
    }
}