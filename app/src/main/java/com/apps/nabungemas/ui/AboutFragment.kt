package com.apps.nabungemas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apps.nabungemas.BuildConfig
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.ui.theme.MyApplicationTheme


//class AboutFragment : Fragment() {
////    private var _binding:FragmentAboutBinding? = null
////
////    private val binding get() = _binding!!
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        _binding= FragmentAboutBinding.inflate(inflater,container,false)
////        val view = binding.root
////        return view
//        return ComposeView(requireContext()).apply {
//            setContent {
//                MyApplicationTheme(darkTheme = false) {
//                    AboutScreen()
//                }
//            }
//        }
//    }
//
////    override fun onDestroyView() {
////        super.onDestroyView()
////        _binding = null
////    }
//
//
//}
@Composable
fun AboutScreen(){
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "About",
                version = 2,
                navigateUp = {})
        },
        backgroundColor = Color(0xFFF4F9FB)
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(id = R.drawable.ic_saving_gold),
                contentDescription = ""
            )
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 10.dp))
                    .background(color = colorResource(id = R.color.yellow_200))
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                    color = Color(0xFF5F5E59)

                    )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.about_statement),
                style = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Center)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.version,BuildConfig.VERSION_NAME),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text =
                stringResource(id = R.string.copyright),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AboutPreview(){
    MyApplicationTheme(darkTheme = false) {
        AboutScreen()
    }
}