package com.apps.nabungemas.ui

import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.ui.theme.MyApplicationTheme



class GraphFragment : Fragment() {
//    private var _binding: FragmentGraphBinding? = null
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        _binding= FragmentGraphBinding.inflate(inflater,container,false)
//        val view = binding.root
//        return view
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme(darkTheme = false) {
                    GraphScreen()
                }
            }
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewLifecycleOwner.lifecycleScope.launch {
//            val data = GoldPriceApi.retrofitService.getPrice()
//            binding.text.text = data.toString()
//        }

//    }

}
@Composable
fun GraphScreen(){
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Graph",
                version = 2,
                navigateUp = {})
        },
        backgroundColor = Color(0xFFF4F9FB)
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                modifier = Modifier,
                text = "data",
                style = MaterialTheme.typography.body2
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GraphPreview(){
    MyApplicationTheme(darkTheme = false) {
        GraphScreen()
    }
}