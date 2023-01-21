package com.apps.nabungemas.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import org.w3c.dom.Text


//class AddTransactionFragment : Fragment() {
////    private val viewModel:TransactionViewModel by activityViewModels {
////        TransactionViewModelFactory((activity?.application as DataApplication).database.transactionDao())
////    }
////
////
////    private var _binding: FragmentAddTransactionBinding? = null
////    private val binding get() = _binding!!
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        _binding = FragmentAddTransactionBinding.inflate(inflater,container,false)
////        val view = binding.root
////        return view
//        return ComposeView(requireContext()).apply {
//            setContent {
//                MyApplicationTheme(darkTheme = false) {
//                    AddTransactionScreen()
//                }
//            }
//        }
//    }
//
//
////    @RequiresApi(Build.VERSION_CODES.N)
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////        binding.btnSave.setOnClickListener {
////            addNewTransaction()
////        }
////        binding.btnCancel.setOnClickListener {
////            findNavController().navigateUp()
////        }
////
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            val time = LocalDateTime.now()
////            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
////
////            binding.etDate.setText(time.format(formatter).toString(), TextView.BufferType.SPANNABLE)
////        }
////
////        val items = resources.getStringArray(R.array.category_saving).toList()
////        val adapter = ArrayAdapter(requireContext(), R.layout.list_dropdown, items)
////        binding.tvCategory.setAdapter(adapter)
////
////        val products = resources.getStringArray(R.array.category_product).toList()
////        val adapterProduct = ArrayAdapter(requireContext(), R.layout.list_dropdown, products)
////        binding.tvProduct.setAdapter(adapterProduct)
////
////        binding.btnDate.setOnClickListener {
////            val datePicker =
////                MaterialDatePicker.Builder.datePicker()
////                    .setTitleText("Select date")
////                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
////                    .build()
////
////            datePicker.addOnPositiveButtonClickListener {
////                // Respond to positive button click.
////                val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.US)
////                val date = dateFormatter.format(Date(it))
////                binding.etDate.setText(date)
////                Toast.makeText(context, "$date is selected", Toast.LENGTH_LONG).show()
////            }
////            datePicker.addOnNegativeButtonClickListener {
////                // Respond to negative button click.
////            }
////            datePicker.addOnCancelListener {
////                // Respond to cancel button click.
////            }
////            datePicker.addOnDismissListener {
////                // Respond to dismiss events.
////            }
////            datePicker.show(parentFragmentManager,"tag")
////        }
////    }
////
////
////
////    private fun addNewTransaction() {
////        if(isEntryValid()){
////            viewModel.addNewTransaction(
////                binding.tvCategory.text.toString(),
////                binding.etDate.text.toString(),
////                binding.etPrice.text.toString(),
////                binding.etQuantity.text.toString(),
////                binding.tvProduct.text.toString()
////            )
////            findNavController().navigateUp()
////        }
////    }
////
////    private fun isEntryValid(): Boolean {
////        return viewModel.isEntryValid(
////            binding.tvCategory.text.toString(),
////            binding.etDate.text.toString(),
////            binding.etPrice.text.toString(),
////            binding.etQuantity.text.toString(),
////            binding.tvProduct.text.toString()
////        )
////    }
////
////    override fun onDestroyView() {
////        super.onDestroyView()
////        // Hide keyboard.
////        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
////                InputMethodManager
////        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
////        _binding = null
////    }
//
//
//}

@Composable
fun AddTransactionScreen(
    navigateBack:()->Unit,
    onNavigateUp:()->Unit
) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Add Transaction",
                version = 0,
                navigateUp = onNavigateUp)
        },
        backgroundColor = Color(0xFFFFFDF5)
    )
    { innerPadding ->
        AddTransactionBody(
            modifier = Modifier.padding(innerPadding),
            transactionTable = TransactionTable()
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddTransactionBody(
    modifier: Modifier,
    transactionTable: TransactionTable,
    onValueChange: (TransactionTable) -> Unit = {}
) {
    val options = listOf("1", "2")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(16.dp)
    ) {

        ExposedDropdownMenuBox(modifier = modifier,
            expanded = expanded,
            onExpandedChange = { !expanded }
        ) {
            OutlinedTextField(modifier = modifier.fillMaxWidth(),
                value = selectedOptionText,
                onValueChange = { selectedOptionText = it },
                label = { Text(text = stringResource(id = R.string.category_saving)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                }
            )

            val filteringOptions =
                options.filter { it.contains(selectedOptionText, ignoreCase = true) }

            if (filteringOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    filteringOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            }
                        ) {
                            Text(text = selectionOption)
                        }
                    }
                }

            }
        }
        Row(
            modifier = modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(modifier = modifier
                .weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White),
                value = "",
                label = { Text(text = stringResource(id = R.string.date)) },
                onValueChange = { onValueChange })
            IconButton(modifier = modifier.size(56.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_date),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Color(0xFF00b0ff)
                )

            }
        }
        Row(
            modifier = modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(modifier = modifier
                .weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White),
                value = "",
                label = { Text(text = stringResource(id = R.string.price_hint)) },
                onValueChange = { onValueChange(transactionTable.copy(goldPrice = it.toLong())) })
            Box(
                modifier = modifier.size(56.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    textAlign = TextAlign.Center, modifier = modifier.padding(4.dp),
                    text = stringResource(id = R.string.rupiah),
                    style = MaterialTheme.typography.h6
                )
            }


        }


        Row(modifier = modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(modifier = modifier
                .weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White),
                value = "",
                label = { Text(text = stringResource(id = R.string.quantity_hint)) },
                onValueChange = {})
            Box(
                modifier = modifier.size(56.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    textAlign = TextAlign.Center, modifier = modifier.padding(4.dp),
                    text = stringResource(id = R.string.grams),
                    style = MaterialTheme.typography.h6
                )
            }


        }



        ExposedDropdownMenuBox(modifier = modifier.padding(top = 16.dp),
            expanded = expanded,
            onExpandedChange = { !expanded }
        ) {
            OutlinedTextField(modifier = modifier.fillMaxWidth(),
                value = selectedOptionText,
                onValueChange = { selectedOptionText = it },
                label = { Text(text = stringResource(id = R.string.product)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                }
            )

            val filteringOptions =
                options.filter { it.contains(selectedOptionText, ignoreCase = true) }

            if (filteringOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    filteringOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            }
                        ) {
                            Text(text = selectionOption)
                        }
                    }
                }

            }
        }
        Row(
            modifier = modifier
                .weight(1f)
                .padding(top = 32.dp)
        ) {
            OutlinedButton(
                modifier = modifier
                    .weight(1f)
                    .height(56.dp),
                onClick = { /*TODO*/ },
                border = BorderStroke(2.dp, colorResource(id = R.color.yellow_500)),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.yellow_500),
                    backgroundColor = Color.White
                )
            ) {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.ic_cancel),
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = stringResource(id = R.string.cancel))
            }
            Spacer(modifier = modifier.width(16.dp))

            Button(
                modifier = modifier
                    .weight(1f)
                    .height(56.dp),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.yellow_500),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.ic_save),
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTransactionPreview() {
    MyApplicationTheme(darkTheme = false) {
        AddTransactionScreen(navigateBack = {}, onNavigateUp = {})
    }
}
