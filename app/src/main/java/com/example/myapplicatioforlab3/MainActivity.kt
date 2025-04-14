package com.example.myapplicatioforlab3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicatioforlab3.ui.theme.MyApplicatioForLab3Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicatioForLab3Theme {
                EditText()
            }
        }
    }
}

@Composable
fun EditText(){
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    var clickCount by remember { mutableStateOf(loadClickCount(context)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Количество тапов хомяка: ${clickCount}  "
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.hamster),
            contentDescription = "Тапай хомяка",
            modifier = Modifier
                .clickable { clickCount++
                    saveClickCount(context, clickCount
                    )
                }
        )

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Введите число")
            }
        )
        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("input_text", text)
                context.startActivity(intent)
            }
        ) {
            Text("Открыть второй экран")
        }
    }
}

private fun loadClickCount(context: Context): Int {
    val prefs = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
    return prefs.getInt("CLICK_COUNT", 0) // 0 - значение по умолчанию
}

@SuppressLint("UseKtx")
private fun saveClickCount(context: Context, count: Int) {
    val prefs = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
    prefs.edit()
        .putInt("CLICK_COUNT", count)
        .apply()
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicatioForLab3Theme {
        EditText()
    }
}