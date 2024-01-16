package com.intelin.core.design.component.input

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CTCheckBox(defaultValue: Boolean? = null, label: String = "", onChecked: (Boolean) -> Unit = {}) {
    var localValue by remember {
        mutableStateOf(defaultValue ?: false)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = localValue, onCheckedChange = {
            onChecked(it)
            localValue = it
        })
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
private fun CTCheckBoxPreview() {
    CTCheckBox(defaultValue = true, onChecked = {}, label = "Test Checked")
}