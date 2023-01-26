package com.apps.nabungemas.ui


import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.viewmodel.TransactionTableDetails
import com.apps.nabungemas.viewmodel.TransactionUiState
import com.apps.nabungemas.viewmodel.TransactionViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


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
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: TransactionViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Add Transaction",
                version = 0,
                navigateUp = onNavigateUp
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
    { innerPadding ->
        AddTransactionBody(
            modifier = Modifier.padding(innerPadding),
            transactionUiState = viewModel.addTransactionUiState,
            onValueChange = viewModel::updateAddTransactionUiState,
            onCancelClick = onNavigateUp,
            onSaveClick = {
                viewModel.addNewTransaction()
                onNavigateUp()
            }
        )
    }
}


@Composable
fun AddTransactionBody(
    modifier: Modifier,
    transactionUiState: TransactionUiState,
    onValueChange: (TransactionTableDetails) -> Unit = {},
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(16.dp)
    ) {
        InputForm(
            modifier = modifier,
            transactionTable = transactionUiState.transactionDetails,
            onValueChange = onValueChange
        )
        Row(
            modifier = modifier
                .weight(1f)
                .padding(top = 32.dp)
        ) {
            OutlinedButton(
                modifier = modifier
                    .weight(1f)
                    .height(56.dp),
                onClick = onCancelClick,
                border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colors.primary,
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
                onClick = onSaveClick,
                enabled = transactionUiState.isEntryValid,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputForm(
    modifier: Modifier,
    transactionTable: TransactionTableDetails,
    onValueChange: (TransactionTableDetails) -> Unit = {},
) {
    val optionsSaving = stringArrayResource(id = R.array.category_saving)
    var expandSaving by remember { mutableStateOf(false) }
    var selectedTextSaving by remember { mutableStateOf("") }

    val optionsProduct = stringArrayResource(id = R.array.category_product)
    var expandProduct by remember { mutableStateOf(false) }
    var selectedTextProduct by remember { mutableStateOf("") }
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val datePickerDialog = DatePickerDialog(
        context,
        {_: DatePicker, year: Int, month: Int, day: Int ->
            calendar.set(year, month, day)
            onValueChange(transactionTable.copy(time = dateFormat.format(calendar.time)))
        }, calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )


    ExposedDropdownMenuBox(modifier = modifier,
        expanded = expandSaving,
        onExpandedChange = { expandSaving = !expandSaving }
    ) {
        OutlinedTextField(modifier = modifier.fillMaxWidth(),
            readOnly = true,
            value = transactionTable.savingCategory,
            onValueChange = {},
            label = { Text(text = stringResource(id = R.string.category_saving)) },
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expandSaving
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expandSaving,
            onDismissRequest = {
                expandSaving = false
            }
        ) {
            optionsSaving.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedTextSaving = selectionOption
                        onValueChange(transactionTable.copy(savingCategory = selectionOption))
                        expandSaving = false
                    }
                ) {
                    Text(text = selectionOption)
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
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White),
            value = transactionTable.time,
            label = { Text(text = stringResource(id = R.string.date)) },
            textStyle = MaterialTheme.typography.body1,
            onValueChange = {
            })
        IconButton(modifier = modifier.size(56.dp),
            onClick = { datePickerDialog.show() }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_date),
                contentDescription = null,
                modifier = Modifier.size(36.dp),
                tint = colorResource(id = R.color.blue_500)
            )

        }
    }

    OutlinedTextField(modifier = modifier
        .padding(top = 16.dp)
        .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = transactionTable.goldPrice,
        label = { Text(text = stringResource(id = R.string.price_hint)) },
        textStyle = MaterialTheme.typography.body1,
        leadingIcon = {
            Text(
                text = stringResource(id = R.string.rupiah),
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
        },
        onValueChange = { onValueChange(transactionTable.copy(goldPrice = it)) })

    OutlinedTextField(modifier = modifier
        .padding(top = 16.dp)
        .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = transactionTable.goldQuantity,
        trailingIcon = {
            Text(
                textAlign = TextAlign.Center, modifier = modifier.padding(8.dp),
                text = stringResource(id = R.string.grams),
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
        },
        label = { Text(text = stringResource(id = R.string.quantity_hint)) },
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { onValueChange(transactionTable.copy(goldQuantity = it)) })

    ExposedDropdownMenuBox(modifier = modifier.padding(top = 16.dp),
        expanded = expandProduct,
        onExpandedChange = { expandProduct = !expandProduct }
    ) {
        OutlinedTextField(modifier = modifier.fillMaxWidth(),
            value = transactionTable.product,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = stringResource(id = R.string.product)) },
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expandProduct
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expandProduct,
            onDismissRequest = {
                expandProduct = false
            }
        ) {
            optionsProduct.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedTextProduct= selectionOption
                        onValueChange(transactionTable.copy(product = selectionOption))
                        expandProduct = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTransactionPreview() {
    MyApplicationTheme(darkTheme = false) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = "Add Transaction",
                    version = 0,
                    navigateUp = { })
            },
            backgroundColor = MaterialTheme.colors.background
        )
        { innerPadding ->
            AddTransactionBody(
                modifier = Modifier.padding(innerPadding),
                transactionUiState = TransactionUiState(
                    transactionDetails = TransactionTableDetails(
                        savingCategory = "Tabungan Menikah",
                        goldPrice = "900000",
                    goldQuantity = "1.0",
                    product = "Antam")),
                onCancelClick = { },
                onSaveClick = {
                }
            )
        }
    }
}
