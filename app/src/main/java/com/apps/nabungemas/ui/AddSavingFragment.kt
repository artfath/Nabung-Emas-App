package com.apps.nabungemas.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apps.nabungemas.DataApplication
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.databinding.FragmentAddSavingBinding
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.viewmodel.TransactionViewModel
import com.apps.nabungemas.viewmodel.TransactionViewModelFactory


class AddSavingFragment : Fragment() {
//    private val viewModel: TransactionViewModel by activityViewModels {
//        TransactionViewModelFactory((activity?.application as DataApplication).database.transactionDao())
//    }
//
//    private var _binding: FragmentAddSavingBinding? = null
//    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        _binding = FragmentAddSavingBinding.inflate(inflater,container,false)
//        val view = binding.root
//        return view
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme(darkTheme = false) {
                    AddSavingScreen()
                }
            }
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val items = resources.getStringArray(R.array.category_saving).toList()
//        val adapter = ArrayAdapter(requireContext(), R.layout.list_dropdown, items)
//        binding.tvCategory.setAdapter(adapter)
//        binding.btnCancel.setOnClickListener {
//            findNavController().navigateUp()
//        }
//        binding.btnSave.setOnClickListener {
//            addNewTransaction()
//        }
//    }
//
//    private fun addNewTransaction() {
//        if(isEntryValid()){
//            viewModel.addNewSaving(
//                binding.tvCategory.text.toString(),
//                binding.etTarget.text.toString()
//            )
//            findNavController().navigateUp()
//        }
//    }
//
//    private fun isEntryValid(): Boolean {
//        return !(binding.tvCategory.text.toString().isBlank() ||
//                binding.etTarget.text.toString().isBlank())
//
//    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Hide keyboard.
//        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
//                InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
//        _binding = null
//    }

}
@Composable
fun AddSavingScreen() {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Add Saving",
                version = 2,
                navigateUp = {})
        },
        backgroundColor = Color(0xFFFFFDF5)
    )
    { innerPadding ->
        AddSavingBody(
            modifier = Modifier.padding(innerPadding),
            transactionTable = TransactionTable()
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddSavingBody(
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
                label = { Text(text = stringResource(id = R.string.target_tabungan)) },
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
                border = BorderStroke(2.dp, Color(0xFFffd740)),
                shape = RoundedCornerShape(10.dp)
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
                shape = RoundedCornerShape(10.dp)
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
        AddSavingScreen()
    }
}