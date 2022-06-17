package ru.sigarev.whattowear.ui.home


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sigarev.whattowear.R
import ru.sigarev.whattowear.domain.models.LocationWithTemperature
import ru.sigarev.whattowear.ui.destinations.DetailLocationScreenDestination
import ru.sigarev.whattowear.ui.destinations.GeolocationSelectionScreenDestination
import ru.sigarev.whattowear.ui.detail_location.DetailScreenNavArgs

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val stateScreen by viewModel.state.collectAsState()
    var tabIndex = remember(stateScreen.filter) { stateScreen.filter.ordinal }
    val tabTitles = remember {
        TabContent.values().toList()
    }

    HomeScreenContent(
        stateScreen,
        tabIndex,
        tabTitles,
        onTabClick = { index ->
            viewModel.processOnClickTab(index)
        },
        onClickAddingLocation = {
            navigator.navigate(GeolocationSelectionScreenDestination())
        },
        onClickLocation = {
            navigator.navigate(DetailLocationScreenDestination(DetailScreenNavArgs(it.toLong())))
        },
        onClickAddFavorite = {
            viewModel.processOnClickFavorite(it, true)
        },
        onClickRemoveFavorite = {
            viewModel.processOnClickFavorite(it, false)
        },
        onRefresh = {
            viewModel.processOnRefresh()
        }
    )
}

@Composable
fun HomeScreenContent(
    stateScreen: HomeState,
    selectedItem: Int,
    tabTitles: List<TabContent>,
    onTabClick: (Int) -> Unit,
    onClickAddingLocation: () -> Unit,
    onClickLocation: (Int) -> Unit,
    onClickAddFavorite: (Int) -> Unit,
    onClickRemoveFavorite: (Int) -> Unit,
    onRefresh: () -> Unit
) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Column(modifier = Modifier.fillMaxSize()) {
            TabRow(selectedTabIndex = selectedItem) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(selected = selectedItem == index,
                        onClick = { onTabClick(index) },
                        text = { Text(text = stringResource(id = title.stringId)) })
                }
            }
            stateScreen.locationsWithTemperature?.let { locations ->
                if (locations.isEmpty())
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "You don't have any added locations.\nTap the button below to get started.",
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.body1,
                        )
                    }
                else
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(stateScreen.isRefreshing),
                        onRefresh = { onRefresh() }
                    ) {
                        LazyColumn {
                            items(locations) { locationInList ->
                                LocationListComponent(
                                    locationInList, onClickLocation,
                                    onClickAddFavorite = onClickAddFavorite,
                                    onClickRemoveFavorite = onClickRemoveFavorite
                                )
                            }
                        }
                    }
            }
        }

        AddFloatingButtonComponent(
            Modifier.padding(end = 16.dp, bottom = 32.dp),
            onClickAddingLocation
        )
    }
}

@Composable
fun LocationListComponent(
    locationWithTemperature: LocationWithTemperature,
    onClick: (Int) -> Unit,
    onClickAddFavorite: (Int) -> Unit,
    onClickRemoveFavorite: (Int) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .clickable {
                    onClick(locationWithTemperature.location.uid)
                }
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(locationWithTemperature.location.name)
                Text(
                    stringResource(
                        id = R.string.item_location_list_subtitle,
                        locationWithTemperature.currentTemperature.toString()
                    )
                )
            }
            if (locationWithTemperature.location.isFavorite)
                FavoriteIconButton(R.drawable.ic_favorite) {
                    onClickRemoveFavorite(locationWithTemperature.location.uid)
                }
            else
                FavoriteIconButton(R.drawable.ic_favorite_border) {
                    onClickAddFavorite(locationWithTemperature.location.uid)
                }
        }
        Spacer(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.4f))
        )
    }
}

@Composable
fun FavoriteIconButton(
    @DrawableRes iconSrc: Int,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(
            painterResource(id = iconSrc),
            contentDescription = null
        )
    }
}

@Composable
fun AddFloatingButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null
        )
    }
}

enum class TabContent(val stringId: Int) {
    All(R.string.home_tab_all),
    Favorites(R.string.home_tab_favorites);
}