package ru.sigarev.whattowear.ui.geolocation_selection

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    val state by viewModel.state.collectAsState()
    if (state.isOpenNextStep) {
        navigator.navigate(SettingLocationNameScreenDestination())
        viewModel.clearAction()
    }

    GeolocationSelectionContent(
        onTapPoint = {
            viewModel.processOnTapMap(it.latitude, it.longitude)
        },
        onClickCurrentLocation = { viewModel.processOnClickCurrentLocation() },
        viewModel.effect
    )
    if (state.isMyLocation) {
        FeatureThatRequiresLocationPermission(
            listenerLocation = { lat, lon ->
                viewModel.processCompleteCurrentLocation(lat, lon)
            }, cancel = {
                viewModel.processCancelLocation()
            }
        )
    }
}

@Composable
fun GeolocationSelectionContent(
    onTapPoint: (Point) -> Unit,
    onClickCurrentLocation: () -> Unit,
    effects: Flow<GeolocationSelectionEffect>
) {
    val inputListener = remember {
        object : InputListener {
            override fun onMapTap(p0: Map, p1: Point) {
                onTapPoint(p1)
            }

            override fun onMapLongTap(p0: Map, p1: Point) {
            }
        }
    }
    val mapView = rememberMapViewWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    Box(contentAlignment = Alignment.BottomEnd) {
        AndroidView({ mapView }) {
            mapView.map.addInputListener(inputListener)
            coroutineScope.launch {
                mapView.setOnClickListener {
                }
            }
        }
        CurrentGeoPositionComponent(
            Modifier.padding(end = 16.dp, bottom = 32.dp),
            onClickCurrentLocation
        )
    }

    LaunchedEffect("key") {
        effects
            .onEach {
                if (it is GeolocationSelectionEffect.MoveToLocation)
                    mapView.map.apply {
                        move(
                            CameraPosition(Point(it.lat, it.lon), 14.0f, 0.0f, 0.0f),
                            Animation(Animation.Type.LINEAR, 5f),
                            null
                        )
                    }
            }
            .launchIn(this)
    }
}

@Composable
fun CurrentGeoPositionComponent(
    modifier: Modifier = Modifier,
    onclick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onclick() },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_location_searching),
            contentDescription = null
        )
    }
}

@SuppressLint("MissingPermission")
@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun FeatureThatRequiresLocationPermission(
    listenerLocation: (Double, Double) -> Unit,
    cancel: () -> Unit
) {
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    if (permissionState.allPermissionsGranted) {
        val ctx = LocalContext.current
        val lm = remember { ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager }

        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            RequestUpdatedLocation(LocationManager.GPS_PROVIDER, lm, listenerLocation)
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            RequestUpdatedLocation(LocationManager.NETWORK_PROVIDER, lm, listenerLocation)
        }
    } else {
        Dialog(onDismissRequest = { cancel() }) {
            Surface {
                Column {
                    Text(stringResource(R.string.geolocation_access_to_location))
                    Text(stringResource(R.string.geolocation_access_to_location_description))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = {
                            permissionState.launchMultiplePermissionRequest()
                        }) {
                            Text(stringResource(id = R.string.geolocation_ok))
                        }
                        TextButton(onClick = {
                            cancel()
                        }) {
                            Text(stringResource(R.string.geolocation_cancel))
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
private fun RequestUpdatedLocation(
    provider: String,
    lm: LocationManager,
    update: (Double, Double) -> Unit
) {
    val locationListener = remember {
        LocationListener { location ->
            update(location.latitude, location.longitude)
        }
    }

    val currentLocation = lm.getLastKnownLocation(provider)
    if (currentLocation != null) {
        update(currentLocation.latitude, currentLocation.longitude)
    } else {
        DisposableEffect(provider) {
            lm.requestLocationUpdates(provider, 1, 1.0F, locationListener)
            onDispose {
                lm.removeUpdates(locationListener)
            }
        }
    }
}

private const val TAG = "GSScreen"