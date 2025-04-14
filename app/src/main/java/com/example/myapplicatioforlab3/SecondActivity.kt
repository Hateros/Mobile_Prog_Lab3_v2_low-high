package com.example.myapplicatioforlab3

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicatioforlab3.ui.theme.MyApplicatioForLab3Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicatioForLab3Theme {
                val inputText = intent.getStringExtra("input_text") ?: ""
                val increasedValue = inputText.toInt() + 10
                val savedCount = loadClickCount(this@SecondActivity)

                setContent {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Введённое число +10: $increasedValue",
                        )
                        Text(
                            text = "Натапано хомяков: $savedCount",
                        )

                    }
                }
            }
        }
    }
}

private fun loadClickCount(context: Context): Int {
    val prefs = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
    return prefs.getInt("CLICK_COUNT", 0)
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyApplicatioForLab3Theme {

    }
}