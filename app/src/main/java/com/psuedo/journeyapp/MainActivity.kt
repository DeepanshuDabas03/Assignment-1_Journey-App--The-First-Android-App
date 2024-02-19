package com.psuedo.journeyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psuedo.journeyapp.ui.theme.JourneyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JourneyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    JourneyApp()
                }
            }
        }
    }
}

@Composable
fun JourneyApp() {
    var distanceInKm by remember { mutableStateOf(true) }
    var currentStop by remember { mutableIntStateOf(0) }
    var useRoute2 by remember { mutableStateOf(false) }
    var isComplete by remember{ mutableStateOf(false) }
    val route1 = mapOf(
        "Start" to 0,
        "Punjabi Bagh" to 2,
        "Lajpat Nagar" to 19,
        "Ashram" to 3,
        "Okhla" to 5
    )
    val route2 = mapOf(
        "Start" to 0,
        "Stop 1" to 1,
        "Stop 2" to 2,
        "Stop 3" to 3,
        "Stop 4" to 4,
        "Stop 5" to 5,
        "Stop 6" to 6,
        "Stop 7" to 7,
        "Stop 8" to 8,
        "Stop 9" to 90,
        "Stop 10" to 10,
        "Stop 11" to 11,
        "Stop 12" to 12,
        "Stop 13" to 13,
        "Stop 14" to 14,
        "Stop 15" to 15,
        "Stop 16" to 16,
        "Stop 17" to 17,
        "Stop 18" to 18,
        "Stop 19" to 19,
        "Stop 20" to 20,
        "Stop 21" to 21,
        "Stop 22" to 22
    )


    val currentRoute = if (useRoute2) route2 else route1

    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Journey App", fontWeight = FontWeight.Bold, color = Color.Yellow, fontSize = 25.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(50.dp))
        if(!isComplete and (currentStop < (currentRoute.size - 1))){
            Text(text = if (distanceInKm) "Currently Distance is in km" else "Distance in miles",color=Color.LightGray)
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Button(onClick = { distanceInKm = !distanceInKm }) {
                    Text(text = "Switch units")
                }

                Spacer(modifier = Modifier.width(30.dp))

                if (currentStop < (currentRoute.size - 1)) {
                    Button(onClick = { currentStop++ }) {
                        Text(text = "Next stop")
                    }
                } else {
                    isComplete = true
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            val currentStopName = currentRoute.keys.elementAt(currentStop)
            Text(text = "Current stop: $currentStopName",color= Color.Magenta)
            Spacer(modifier = Modifier.height(20.dp))
            val nextStopName=currentRoute.keys.elementAt(currentStop+1)
            val currentStopDistance = currentRoute[nextStopName]!!

            Text(text = "Distance to next stop: ${if (distanceInKm) currentStopDistance else currentStopDistance * 0.621371} ${if (distanceInKm) "km" else "miles"}",color=Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            val totalDistance = currentRoute.values.sum()
            val totalDistanceCovered = currentRoute.values.take(currentStop + 1).sum()
            val totalDistanceLeft = totalDistance - totalDistanceCovered
            Text(text = "Total distance covered: ${if (distanceInKm) totalDistanceCovered else totalDistanceCovered * 0.621371} ${if (distanceInKm) "km" else "miles"}",color=Color.Cyan)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Total distance left: ${if (distanceInKm) totalDistanceLeft else totalDistanceLeft * 0.621371} ${if (distanceInKm) "km" else "miles"}",color=Color.Green)
            Spacer(modifier = Modifier.height(35.dp))
            LinearProgressIndicator(progress = totalDistanceCovered.toFloat() / totalDistance)
            Spacer(modifier = Modifier.height(35.dp))

            Button(onClick = { useRoute2 = !useRoute2; currentStop = 0 }) {
                Text(text = "Switch route")
            }

            Spacer(modifier = Modifier.height(20.dp))
            if (currentRoute.size > 10) {
                LazyColumn {
                    items(currentRoute.entries.toList()) { entry ->
                        StopCard(entry.key, entry.value, distanceInKm)
                    }
                }
            } else {
                Column {
                    currentRoute.entries.forEach { entry ->
                        StopCard(entry.key, entry.value, distanceInKm)
                    }
                }
            }

        }
        else{
            val totalDistanceCovered = currentRoute.values.sum()
            Spacer(modifier = Modifier.height(50.dp))
            Card(modifier = Modifier
                .width(400.dp)
                .padding(20.dp) ) {
                Column(modifier = Modifier
                    .padding(10.dp)
                    .width(300.dp)
                    .background(Color.Cyan), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Thank You ! Your Journey Is Complete", color = Color.Blue)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text ="Total Distance Covered: ${if (distanceInKm) totalDistanceCovered else totalDistanceCovered * 0.621371} ${if (distanceInKm) "km" else "miles"}",color= Color.Magenta)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { currentStop = 0; isComplete = false }) {
                Text(text = "Restart journey")
            }
        }
    }
}

@Composable
fun StopCard(stop: String, distance: Int, distanceInKm: Boolean) {
    Card(modifier = Modifier.padding(10.dp) ) {
        Column(modifier = Modifier
            .padding(10.dp)
            .width(300.dp)
            .background(Color.Transparent), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stop, color = Color.Blue)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text ="Distance: ${if (distanceInKm) distance else distance * 0.621371} ${if (distanceInKm) "km" else "miles"}",color= Color.Red)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}



@Preview(showBackground = true)
@Composable
fun JourneyAppPreview() {
    JourneyAppTheme {
        JourneyApp()
    }
}
