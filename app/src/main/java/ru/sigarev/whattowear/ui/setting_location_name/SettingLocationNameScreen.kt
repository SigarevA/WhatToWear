package ru.sigarev.whattowear.ui.setting_location_name

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sigarev.whattowear.R
import ru.sigarev.whattowear.ui.destinations.HomeScreenDestination

@RootNavGraph
@Destination
@Composable
fun SettingLocationNameScreen(
    viewModel: SettingsLocationNameViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.state.collectAsState()
    SettingLocationNameContent(
        state,
        navUp = {
            navigator.navigateUp()
        },
        onClickNext = {
            viewModel.processOnClickSave()
            navigator.popBackStack(HomeScreenDestination.route, inclusive = false)
        },
        onNameChange = { newName ->
            viewModel.processNameChange(newName)
        }
    )
}

@Composable
fun SettingLocationNameContent(
    state: SettingsLocationNameState,
    navUp: () -> Unit,
    onClickNext: () -> Unit,
    onNameChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(stringResource(R.string.setting_location_title_screen))
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        onNameChange(it)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onClickNext()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                TextButton(
                    onClick = { onClickNext() }
                ) {
                    Text(stringResource(R.string.setting_location__screen_btn_text))
                }
            }
        }
    }
}