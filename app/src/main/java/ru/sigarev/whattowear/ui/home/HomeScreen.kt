package ru.sigarev.whattowear.ui.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sigarev.whattowear.R
import ru.sigarev.whattowear.ui.destinations.GeolocationSelectionScreenDestination

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

    HomeScreenContent(tabIndex, tabTitles,
        onTabClick = { index ->
            viewModel.processOnClickTab(index)
        },
        onClickAddingLocation = {
            navigator.navigate(GeolocationSelectionScreenDestination())
        }
    )
}

@Composable
fun HomeScreenContent(
    selectedItem: Int,
    tabTitles: List<TabContent>,
    onTabClick: (Int) -> Unit,
    onClickAddingLocation: () -> Unit
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
        }
        FloatingActionButton(
            onClick = { onClickAddingLocation() },
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

enum class TabContent(val stringId: Int) {
    All(R.string.home_tab_all),
    Favorites(R.string.home_tab_favorites);
}