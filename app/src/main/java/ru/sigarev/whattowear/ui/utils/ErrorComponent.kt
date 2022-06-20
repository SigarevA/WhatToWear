package ru.sigarev.whattowear.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.sigarev.whattowear.R

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    retry: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.home_screen_error),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.body1,
        )
        TextButton(onClick = { retry() }) {
            Text(
                text = stringResource(id = R.string.home_screen_retry),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.button,
            )
        }
    }
}