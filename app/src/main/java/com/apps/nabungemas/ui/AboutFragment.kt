package com.apps.nabungemas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.databinding.FragmentAboutBinding
import com.apps.nabungemas.ui.navigation.NavigationDestination
import com.apps.nabungemas.ui.theme.MyApplicationTheme

object AboutDestination: NavigationDestination {
    override val route: String = "about"
    override val title: Int = R.string.about

}
class AboutFragment : Fragment() {
//    private var _binding:FragmentAboutBinding? = null
//
//    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        _binding= FragmentAboutBinding.inflate(inflater,container,false)
//        val view = binding.root
//        return view
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme(darkTheme = false) {
                    AboutScreen()
                }
            }
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }


}
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
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.about_statement),
                style = MaterialTheme.typography.body2
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