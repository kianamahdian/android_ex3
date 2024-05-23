package com.example.exercise3

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.io.File
import androidx.compose.foundation.layout.padding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LogScreen()
                }
            }
        }
    }
}

@Composable
fun LogScreen() {
    val logs = remember { readLogsFromFile() }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(logs) { log ->
            Text(text = log, modifier = Modifier.padding(8.dp))
        }
    }
}

fun readLogsFromFile(): List<String> {
    val logFile = File("/data/data/com.example.exercise3/files/log.txt")
    if (!logFile.exists()) return emptyList()
    return logFile.readLines().reversed()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        LogScreen()
    }
}
