package ru.sigarev.whattowear.ui.geolocation_selection

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import kotlinx.coroutines.launch
import ru.sigarev.whattowear.R
import ru.sigarev.whattowear.ui.destinations.SettingLocationNameScreenDestination
import ru.sigarev.whattowear.ui.utils.rememberMapViewWithLifecycle

@RootNavGraph
@Destination
@Composable
fun GeolocationSelectionScreen(
    viewModel: GeolocationSelectionViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state = viewModel.state.collectAsState()
    if (state.value.isOpenNextStep) {
        navigator.navigate(SettingLocationNameScreenDestination())
        viewModel.clearAction()
    }

    GeolocationSelectionContent {
        viewModel.processOnTapMap(it.latitude, it.longitude)
    }
}

@Composable
fun GeolocationSelectionContent(
    onTapPoint: (Point) -> Unit
) {
    val mapView = rememberMapViewWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    Box(contentAlignment = Alignment.BottomEnd) {
        AndroidView({ mapView }) {
            mapView.map.addInputListener(object : InputListener {
                override fun onMapTap(p0: Map, p1: Point) {
                    onTapPoint(p1)
                    p1.latitude
                    p1.longitude
                }

                override fun onMapLongTap(p0: Map, p1: Point) {
                    Log.d("HomeScreen", "long onMapTap")
                }

            })
            mapView.setOnClickListener {
                Log.d("HomeScreen", "click")
            }
            coroutineScope.launch {
                mapView.setOnClickListener {
                    Log.d("HomeScreen", "click")
                }
            }
        }
        FloatingActionButton(
            onClick = { },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            modifier = Modifier.padding(end = 16.dp, bottom = 32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location_searching),
                contentDescription = null
            )
        }
    }
}

private const val TAG = "GSScreen"