package com.apps.nabungemas.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.viewmodel.SavingDetails
import com.apps.nabungemas.viewmodel.SavingUiState
import com.apps.nabungemas.viewmodel.TransactionViewModel



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
        backgroundColor = MaterialTheme.colors.background
    )
    { innerPadding ->
        AddSavingBody(
            modifier = Modifier.padding(innerPadding),
            savingUiState = viewModel.addSavingUiState ,
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
                textStyle = MaterialTheme.typography.body1,
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

            OutlinedTextField(modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White),
                value = savingUiState.savingDetails.target,
                label = { Text(text = stringResource(id = R.string.target_tabungan)) },
                textStyle = MaterialTheme.typography.body1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Text(
                        text = stringResource(id = R.string.rupiah),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                },
                onValueChange = {
                    onValueChange(savingUiState.savingDetails.copy(target = it)) })


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
                enabled = savingUiState.isEntryValid,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
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