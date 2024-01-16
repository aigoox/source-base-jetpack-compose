package com.intelin.core.design.component.button

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.intelin.core.design.theme.LocalMeasureTheme

@SuppressLint("ModifierParameter")
@Composable
fun CTButton(
    modifier: Modifier = Modifier,
    color: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
    enabled: Boolean = true,
    label: String,
    onClick: () -> Unit = {}
) {

    Button(
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        shape = RoundedCornerShape(LocalMeasureTheme.current.radiusButton)
    ) {
        Text(
            text = label,
            fontSize = LocalMeasureTheme.current.sizeTextStandard,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Composable
fun CTButtonWithModifier(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {

    Box(modifier = modifier) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = onClick, colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
            )
        ) {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CTButtonPreview() {
    CTButton(label = "Button test", enabled = false)
}
