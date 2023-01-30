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
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.utils.CurrencyAmount.currencyId
import com.apps.nabungemas.utils.Time.getTime
import com.apps.nabungemas.viewmodel.GoldViewModel


@Composable
fun HomeScreen(
    navigateToAddTransaction: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GoldViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val target by viewModel.allTargetState.collectAsState(initial = 0)
    val saving by viewModel.allSavingState.collectAsState(initial = 0)
    val percentage by viewModel.percentageState.collectAsState(initial = 0.0)
    val listHeader = listOf(target.toString(),
        saving.toString(),
        percentage.toString())
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
            percentage = percentage,
            goldCurrency = goldCurrency
        )

    }


}

@Composable
fun HomeBody(
    modifier: Modifier,
    listHeader: List<String>,
    percentage: Double?,
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
            list = listHeader,
            percentage = percentage
        )
        Text(
            modifier = modifier.padding(top = 24.dp, start = 16.dp),
            text = stringResource(id = R.string.referensi_hari_ini),
            style = MaterialTheme.typography.h6,
            color = Color.Gray
        )
        GoldCard(modifier = modifier, goldCurrency = goldCurrency)
        CurrencyCard(date = goldCurrency?.dateCurrency, currency = goldCurrency?.currency)
    }
}


@Composable
fun Header(
    modifier: Modifier,
    list: List<String>,
percentage:Double?
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
                        text = stringResource(id = R.string.total_target, currencyId( list[0])),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black
                    )
                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = stringResource(id = R.string.total_saving, currencyId( list[1])),
                        style = MaterialTheme.typography.body2,
                        color = Color.Black
                    )
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_date),
                            contentDescription = null,
                            modifier = modifier.size(24.dp),
                            tint = colorResource(id = R.color.blue_500)
                        )
                        Text(
                            modifier = modifier.padding(start = 4.dp),
                            text = getTime() ?: "",
                            style = MaterialTheme.typography.caption,
                            color = Color.Black
                        )



                    }

                }
                Box(modifier = modifier.weight(0.4f)) {
                    Text(
                        modifier = modifier,
                        text = stringResource(id = R.string.percentage,percentage ?: 0.0),
                        style = MaterialTheme.typography.h5,
                        color = Color.Black
                    )
                }

            }
        }
    }

}

@Composable
fun GoldCard(modifier: Modifier,
             goldCurrency: GoldCurrencyTable?) {
    val goldDifferent = goldCurrency?.priceDifferent?: 0.0
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .shadow(6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Row(modifier = modifier) {
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
                Text(
                    text = stringResource(id = R.string.emas),
                    style = MaterialTheme.typography.h5,
                    color = colorResource(id = R.color.yellow_700)
                )
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = goldCurrency?.dateGold ?: getTime().toString(),
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = stringResource(
                        id = R.string.price_pergram,
                        currencyId(goldCurrency?.priceGram24k.toString())

                    ),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )

                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = stringResource(
                        id = R.string.price_previous,
                        currencyId(
                        goldCurrency?.prevPrice.toString())
                    ),
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )
                Row(modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {

                    if(goldDifferent < 0.0){
                        Icon(modifier = modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_down),
                            contentDescription = null,
                            tint = colorResource(id = R.color.red_500)
                        )
                    }else {
                        Icon(modifier = modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_up),
                            contentDescription = null,
                            tint = colorResource(id = R.color.green_500)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = currencyId(goldDifferent.toString()),
                        style = MaterialTheme.typography.caption,
                        color = Color.Black

                    )
                }
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
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.ic_forex),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp)
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
                    text = currencyId(currency.toString()),
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
        Column {
            Header(modifier = Modifier, listOf("90000", "80000", "40 %"), percentage = 0.0)
            Text(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp),
                text = stringResource(id = R.string.referensi_hari_ini),
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
            GoldCard(modifier = Modifier,
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