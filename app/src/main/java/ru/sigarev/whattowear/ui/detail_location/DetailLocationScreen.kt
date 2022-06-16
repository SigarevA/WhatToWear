package ru.sigarev.whattowear.ui.detail_location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sigarev.whattowear.R
import ru.sigarev.whattowear.domain.models.LocationWithWeather
import java.text.SimpleDateFormat
import java.util.*

@Composable
@RootNavGraph
@Destination(navArgsDelegate = DetailScreenNavArgs::class)
fun DetailLocationScreen(
    viewModel: DetailLocationViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.state.collectAsState()
    DetailLocationContent(state,
        navUp = {
            navigator.navigateUp()
        },
        onClickDelete = {
            viewModel.processOnClickDelete()
        },
        onRefresh = {
            viewModel.processRefresh()
        }
    )

    if (state.isDeletion) {
        LocationDeletionDialog(
            dismiss = { viewModel.processDismissDeletion() },
            onNegativeClick = { viewModel.processLocationDelete() }
        )
    }

    LaunchedEffect(state.uid) {
        viewModel.viewEffects
            .onEach {
                when (it) {
                    DetailLocationViewEffect.CloseDetailScreen -> {
                        navigator.navigateUp()
                    }
                }
            }
            .launchIn(this)
    }
}

@Composable
fun LocationDeletionDialog(
    dismiss: () -> Unit,
    onNegativeClick: () -> Unit
) {
    Dialog(onDismissRequest = { dismiss() }) {
        Surface(
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    stringResource(R.string.detail_location_screen_title_dialog),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    stringResource(R.string.detail_location_screen_subtitle_dialog),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.65f),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    TextButton(onClick = { dismiss() }) {
                        Text(
                            stringResource(R.string.detail_location_screen_cancel_dialog),
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.button
                        )
                    }
                    TextButton(onClick = { onNegativeClick() }) {
                        Text(
                            stringResource(id = R.string.detail_location_screen_delete_dialog),
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailLocationContent(
    state: DetailLocationState,
    navUp: () -> Unit,
    onClickDelete: () -> Unit,
    onRefresh: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (state.isSuccess)
                        Text(state.content?.location?.name ?: "")
                },
                navigationIcon = {
                    IconButton(onClick = { navUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onClickDelete() }) {
                        Icon(painterResource(id = R.drawable.ic_delete), contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (state.isSuccess) {
                SuccessDetailLocationContent(
                    state.isRefresh,
                    state.content!!,
                    onRefresh
                )
            }
        }
    }
}

@Composable
fun SuccessDetailLocationContent(
    isRefresh: Boolean,
    content: LocationWithWeather,
    onRefresh: () -> Unit
) {
    val dateInfo = remember(content) {
        SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(Date(content.weather.currentDt))
    }
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefresh),
        onRefresh = { onRefresh() }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = dateInfo,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_cloud),
                    contentDescription = null,
                    modifier = Modifier.size(72.dp)
                )
                Text(
                    content.weather.currentTemperature.toString(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.h3
                )
            }
            Text(
                text = content.weather.mainWeather,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TemperatureForecast(
                    time = stringResource(id = R.string.detail_location_morning),
                    temperature = content.weather.dayTemperature.morn
                )
                TemperatureForecast(
                    time = stringResource(id = R.string.detail_location_day),
                    temperature = content.weather.dayTemperature.day
                )
                TemperatureForecast(
                    time = stringResource(id = R.string.detail_location_evening),
                    temperature = content.weather.dayTemperature.eve
                )
                TemperatureForecast(
                    time = stringResource(id = R.string.detail_location_night),
                    temperature = content.weather.dayTemperature.night
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 16.dp)
                    .background(color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f))
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_wind),
                    null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    stringResource(id = R.string.detail_location_wind, content.weather.windSpeed),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_humidity), null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    stringResource(
                        id = R.string.detail_location_humidity,
                        content.weather.humidity
                    ),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_pressure),
                    null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    stringResource(
                        id = R.string.detail_location_current_pressure,
                        content.weather.pressure
                    ),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}

@Composable
fun TemperatureForecast(
    time: String,
    temperature: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
            style = MaterialTheme.typography.body1
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_outline_cloud),
            contentDescription = null,
            modifier = Modifier.height(24.dp)
        )
        Text(
            text = temperature.toString(),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
            style = MaterialTheme.typography.body1
        )
    }
}