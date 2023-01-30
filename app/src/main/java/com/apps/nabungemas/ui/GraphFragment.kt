package com.apps.nabungemas.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.model.GoldUpdatePrice
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.utils.CurrencyAmount.currencyId
import com.apps.nabungemas.utils.Time
import com.apps.nabungemas.viewmodel.GoldUiState
import com.apps.nabungemas.viewmodel.GoldViewModel
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility


@Composable
fun GraphScreen(
    viewModel: GoldViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val listData = remember { viewModel.itemGoldPrice }
    val listDate = remember {
        viewModel.itemGoldDate
    }
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Graph",
                version = 2,
                navigateUp = {})
        },
        backgroundColor = Color(0xFFF4F9FB)
    ) { innerPadding ->
        GraphBody(
            modifier = Modifier.padding(innerPadding),
            goldUiState = viewModel.goldUiState,
            listData = listData,
            listDate = listDate
        )


    }
}


@Composable
fun GraphBody(
    modifier: Modifier,
    goldUiState: GoldUiState,
    listData: List<Number>,
    listDate: List<String>
) {
    Column {
        GraphGold(modifier = modifier, listData = listData, listDate = listDate)
        Text(
            modifier = modifier.padding(top = 16.dp, start = 16.dp, bottom = 8.dp),
            text = stringResource(id = R.string.referensi_hari_ini),
            style = MaterialTheme.typography.h6,
            color = Color.Gray
        )
        when (goldUiState) {
            is GoldUiState.Loading -> {
                LoadingScreen(modifier)
            }
            is GoldUiState.Success -> {
                if (goldUiState.goldData.data.isNullOrEmpty()) {
                    Text(
                        modifier = Modifier,
                        text = "No Data",
                        style = MaterialTheme.typography.body2
                    )
                } else {


                    GoldList(modifier = modifier, itemList = goldUiState.goldData.data)

                }
            }
            is GoldUiState.Error -> {}
        }
    }


}

@Composable
fun GraphGold(
    modifier: Modifier,
    listData: List<Number>,
    listDate: List<String>
) {
    val style = LineGraphStyle(
        visibility = LinearGraphVisibility(
            isHeaderVisible = true,
            isYAxisLabelVisible = false,
            isCrossHairVisible = true
        ),
        colors = LinearGraphColors(
            pointColor = colorResource(id = R.color.blue_500),
            lineColor = colorResource(id = R.color.blue_500),

            clickHighlightColor = Color(0xAAffd740),
            fillGradient = Brush.verticalGradient(
                listOf(colorResource(id = R.color.blue_200), Color.Transparent)
            )
        ),
     height = 400.dp,
     paddingValues = PaddingValues(16.dp)
    )
    Box(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(0.4f)
        .padding(8.dp)) {

        val clickedValue: MutableState<Pair<Any, Any>?> =
            rememberSaveable { mutableStateOf(null) }

        LineGraph(
            xAxisData = listDate.map {
                GraphData.String(it)
            },
            yAxisData = listData,
            style = style,
            onPointClicked = {
                clickedValue.value = it
            }
        )
        clickedValue.value?.let {
            Row(
                modifier = modifier
                    .padding(all = 25.dp)
            ) {
                Text(text = "Price: ", color = Color.Black)
                Text(
                    text = currencyId(it.second.toString()),
                    color = colorResource(id = R.color.yellow_700),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }

}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }


    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    LoadingScreen(modifier = Modifier)
}

@Composable
fun GoldList(
    modifier: Modifier,
    itemList: List<GoldUpdatePrice>
) {
    LazyColumn {
        items(items = itemList) {
            GoldItem(modifier = modifier, gold = it)
        }

    }
}

@Composable
fun GoldItem(
    modifier: Modifier,
    gold: GoldUpdatePrice
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            .shadow(6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier.size(80.dp),
                painter = painterResource(id = R.drawable.ic_gold),
                contentDescription = ""
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.weight(1f),
                        text = gold.name ?: "Emas",
                        style = MaterialTheme.typography.h5,
                        color = colorResource(id = R.color.yellow_700)
                    )
                    Text(
                        modifier = modifier.padding(start = 8.dp),
                        text = Time.timestampDate(gold.time),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black
                    )
                }

                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = stringResource(
                        id = R.string.price_pergram,
                        currencyId(gold.goldPrice ?: "0")
                    ),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )

                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = stringResource(
                        id = R.string.price_previous,
                        currencyId(gold.lastPrice ?: "0")
                    ),
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (gold.change!! < 0) {
                        Icon(
                            modifier = modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_down),
                            contentDescription = null,
                            tint = colorResource(id = R.color.red_500)
                        )
                    } else {
                        Icon(
                            modifier = modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_up),
                            contentDescription = null,
                            tint = colorResource(id = R.color.green_500)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = currencyId(gold.change.toString()),
                        style = MaterialTheme.typography.caption,
                        color = Color.Black

                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoldItemPreview() {
    MyApplicationTheme(darkTheme = false) {
        GoldItem(
            modifier = Modifier, gold = GoldUpdatePrice(
                "Antam", "900000",
                "200000", -1000, 1674808044000
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GraphPreview() {
    MyApplicationTheme(darkTheme = false) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = "Graph",
                    version = 2,
                    navigateUp = {})
            },
            backgroundColor = Color(0xFFF4F9FB)
        ) { innerPadding ->
            GraphBody(
                modifier = Modifier.padding(innerPadding),
                goldUiState = GoldUiState.Success(
                    goldData = GoldUpdatePrice.GoldResponse(
                        mutableListOf(
                            GoldUpdatePrice(
                                "Antam", "900000",
                                "200000", -1000, 1674808044000
                            ),
                            GoldUpdatePrice(
                                "UBS", "200000",
                                "200000", 2000, 1674808044000
                            )
                        )
                    )

                ),
                listData = listOf(100, 40, 60, 450, 460, 30, 50),
                listDate = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat")
            )


        }
    }
}