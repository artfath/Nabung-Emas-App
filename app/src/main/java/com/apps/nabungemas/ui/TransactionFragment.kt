package com.apps.nabungemas.ui

import android.os.Bundle
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.databinding.FragmentTransactionBinding
import com.apps.nabungemas.ui.adapter.TransactionListAdapter
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.viewmodel.TransactionViewModel
import com.apps.nabungemas.viewmodel.TransactionViewModelFactory


class TransactionFragment : Fragment() {
//    private val viewModel: TransactionViewModel by activityViewModels {
//        TransactionViewModelFactory(
//            (activity?.application as DataApplication).database.transactionDao()
//        )
//    }
//    private var _binding: FragmentTransactionBinding? = null
//    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        _binding = FragmentTransactionBinding.inflate(inflater,container,false)
//        val view = binding.root
//        return view
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme(darkTheme = false) {
                    TransactionScreen()
                }
            }
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val adapter = TransactionListAdapter()
//
//
//        viewModel.allTransaction.observe(viewLifecycleOwner, {
//            adapter.submitList(it)
//        })
//        binding.apply {
//            rvTransaction.setHasFixedSize(true)
//            rvTransaction.adapter = adapter
//        }
//        topMenu()
//
//    }
//
//    private fun topMenu() {
//        binding.toolbar.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.add -> {
//                    findNavController().navigate(R.id.action_transactionFragment_to_addTransactionFragment)
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
fun TransactionBody(
    itemList: List<TransactionTable>,
    modifier: Modifier
) {
    Column() {
        if (itemList.isEmpty()) {
            Text(text = "No data")
        } else {
            TransactionList(modifier = modifier,itemList)
        }
    }


}

@Composable
fun TransactionList(modifier: Modifier,itemList: List<TransactionTable>) {
    LazyColumn() {
        items(items = itemList) {
            TransactionItem(modifier = modifier,it)
        }
    }
}

@Composable
fun TransactionItem(modifier: Modifier,
                    transaction: TransactionTable) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
            .shadow(6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)
            .padding(6.dp)

    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier.size(60.dp),
                painter = painterResource(id = R.drawable.ic_transaction),
                contentDescription = ""
            )
            Column(modifier = modifier.padding(6.dp)) {
                Text(text = "Tabungan", style = MaterialTheme.typography.h6)
                Row() {
                    Text(
                        modifier = modifier,
                        text = transaction.time,
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Text(
                        modifier = modifier,
                        text = transaction.goldPrice.toString(),
                        style = MaterialTheme.typography.body1
                    )
                }
                Row() {
                    Text(
                        modifier = modifier,
                        text = transaction.product,
                        style = MaterialTheme.typography.body1,
                        color = Color(0xFFc8a600)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Text(
                        modifier = modifier,
                        text = transaction.goldQuantity.toString(),
                        style = MaterialTheme.typography.body2,
                        color = Color(0xFF0081cb)
                    )
                }
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun TransactionItemPreview(){
    TransactionItem(modifier = Modifier, transaction = TransactionTable(0,"20 januari 2023","tabungan menikah",20000,1.0,"antam"))
}
@Composable
fun TransactionScreen() {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Transaction",
                version = 1,
                navigateUp = {})
        },
        backgroundColor = Color(0xFFF4F9FB)
    )
    { innerPadding ->
        TransactionBody(
            itemList = listOf(TransactionTable(0,"20 januari 2023","tabungan menikah",20000,1.0,"antam"),
                TransactionTable(1,"20 januari 2023","tabungan menikah",20000,1.0,"antam")),
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionPreview() {
    MyApplicationTheme(darkTheme = false) {
        TransactionScreen()
    }
}