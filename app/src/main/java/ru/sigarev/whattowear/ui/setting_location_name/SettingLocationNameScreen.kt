package ru.sigarev.whattowear.ui.setting_location_name

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ru.sigarev.whattowear.R

@RootNavGraph
@Destination
@Composable
fun SettingLocationNameScreen(
    navigator: DestinationsNavigator
) {
    SettingLocationNameContent(
        navUp = { //navigator.navigateUp()
             },
        onClickNext = { }
    )
}

@Composable
fun SettingLocationNameContent(
    navUp: () -> Unit,
    onClickNext: () -> Unit
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
                    Text("LocationName")
                }
            )
        }
    ) {
        Box {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
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
                    Text("Next")
                }
            }
        }
    }
}