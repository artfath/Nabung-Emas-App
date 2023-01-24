package com.apps.nabungemas.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.viewmodel.SavingDetails
import com.apps.nabungemas.viewmodel.SavingUiState
import com.apps.nabungemas.viewmodel.TransactionViewModel


//class AddSavingFragment : Fragment() {
////    private val viewModel: TransactionViewModel by activityViewModels {
////        TransactionViewModelFactory((activity?.application as DataApplication).database.transactionDao())
////    }
////
////    private var _binding: FragmentAddSavingBinding? = null
////    private val binding get() = _binding!!
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        _binding = FragmentAddSavingBinding.inflate(inflater,container,false)
////        val view = binding.root
////        return view
//        return ComposeView(requireContext()).apply {
//            setContent {
//                MyApplicationTheme(darkTheme = false) {
//                    AddSavingScreen()
//                }
//            }
//        }
//    }
//
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////
////        val items = resources.getStringArray(R.array.category_saving).toList()
////        val adapter = ArrayAdapter(requireContext(), R.layout.list_dropdown, items)
////        binding.tvCategory.setAdapter(adapter)
////        binding.btnCancel.setOnClickListener {
////            findNavController().navigateUp()
////        }
////        binding.btnSave.setOnClickListener {
////            addNewTransaction()
////        }
////    }
////
////    private fun addNewTransaction() {
////        if(isEntryValid()){
////            viewModel.addNewSaving(
////                binding.tvCategory.text.toString(),
////                binding.etTarget.text.toString()
////            )
////            findNavController().navigateUp()
////        }
////    }
////
////    private fun isEntryValid(): Boolean {
////        return !(binding.tvCategory.text.toString().isBlank() ||
////                binding.etTarget.text.toString().isBlank())
////
////    }
////    override fun onDestroyView() {
////        super.onDestroyView()
////        // Hide keyboard.
////        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
////                InputMethodManager
////        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
////        _binding = null
////    }
//
//}
@Composable
fun AddSavingScreen(navigateBack :()->Unit,
                    onNavigateUp :()->Unit,
viewModel: TransactionViewModel=viewModel(factory=AppViewModelProvider.Factory)) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Add Saving",
                version = 0,
                navigateUp = onNavigateUp)
        },
        backgroundColor = Color(0xFFFFFDF5)
    )
    { innerPadding ->
        AddSavingBody(
            modifier = Modifier.padding(innerPadding),
            savingUiState = viewModel.savingUiState ,
            onValueChange = viewModel::updateSavingUiState,
            onCancelClick = onNavigateUp,
            onSaveClick = {
                viewModel.addNewSavings()
                onNavigateUp()
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddSavingBody(
    modifier: Modifier,
    savingUiState: SavingUiState,
    onValueChange: (SavingDetails) -> Unit = {},
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    val optionsSaving = stringArrayResource(id = R.array.category_saving)
    var expandSaving by remember { mutableStateOf(false) }
    var selectedTextSaving by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(16.dp)
    ) {

        ExposedDropdownMenuBox(modifier = modifier,
            expanded = expandSaving,
            onExpandedChange = { expandSaving = !expandSaving}
        ) {
            OutlinedTextField(modifier = modifier.fillMaxWidth(),
                readOnly=true ,
                value = savingUiState.savingDetails.savingCategory,
                onValueChange = {  },
                label = { Text(text = stringResource(id = R.string.category_saving)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandSaving
                    )
                }
            )
                ExposedDropdownMenu(
                    expanded = expandSaving,
                    onDismissRequest = {
                        expandSaving= false
                    }
                ) {
                    optionsSaving.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedTextSaving = selectionOption
                                onValueChange(savingUiState.savingDetails.copy(savingCategory = selectionOption))
                                expandSaving = false
                            }
                        ) {
                            Text(text = selectionOption)
                        }
                    }
                }


        }

//        Row(
//            modifier = modifier.padding(top = 16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
            OutlinedTextField(modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White),
                value = savingUiState.savingDetails.target,
                label = { Text(text = stringResource(id = R.string.target_tabungan)) },
                leadingIcon = {
                    Text(
                        text = stringResource(id = R.string.rupiah),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                },
                onValueChange = {
                    onValueChange(savingUiState.savingDetails.copy(target = it)) })
//            Box(
//                modifier = modifier.size(56.dp),
//                contentAlignment = Alignment.Center,
//            ) {
//                Text(
//                    textAlign = TextAlign.Center, modifier = modifier.padding(4.dp),
//                    text = stringResource(id = R.string.rupiah),
//                    style = MaterialTheme.typography.h6
//                )
//            }
//
//        }


        Row(
            modifier = modifier
                .weight(1f)
                .padding(top = 32.dp)
        ) {
            OutlinedButton(
                modifier = modifier
                    .weight(1f)
                    .height(56.dp),
                onClick =  onCancelClick ,
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
                onClick = onSaveClick,
                enabled = savingUiState.isEntryValid,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.yellow_500),
                    contentColor = Color.White)
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
fun AddSavingPreview() {
    MyApplicationTheme(darkTheme = false) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = "Add Saving",
                    version = 0,
                    navigateUp = {})
            },
            backgroundColor = Color(0xFFFFFDF5)
        )
        { innerPadding ->
            AddSavingBody(
                modifier = Modifier.padding(innerPadding),
                savingUiState = SavingUiState() ,
                onValueChange = {},
                onCancelClick = {},
                onSaveClick = { }
            )
        }
    }
}