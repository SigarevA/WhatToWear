package ru.sigarev.whattowear.ui.home


import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import ru.sigarev.whattowear.R

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val stateScreen by viewModel.state.collectAsState()
    var tabIndex = remember(stateScreen.filter) { stateScreen.filter.ordinal }
    val tabTitles = remember {
        TabContent.values().toList()
    }

    HomeScreenContent(tabIndex, tabTitles) { index ->
        viewModel.processOnClickTab(index)
    }
}

@Composable
fun HomeScreenContent(
    selectedItem: Int,
    tabTitles: List<TabContent>,
    onTabClick: (Int) -> Unit
) {
    TabRow(selectedTabIndex = selectedItem) {
        tabTitles.forEachIndexed { index, title ->
            Tab(selected = selectedItem == index,
                onClick = { onTabClick(index) },
                text = { Text(text = stringResource(id = title.stringId)) })
        }
    }
}

enum class TabContent(val stringId: Int) {
    All(R.string.home_tab_all),
    Favorites(R.string.home_tab_favorites);
}