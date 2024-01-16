package com.intelin.core.design.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.intelin.core.design.component.layout.CTAddSpace
import com.intelin.core.design.component.text.CTText
import com.intelin.core.design.repository.data.ModeAlignmentDirection
import com.intelin.core.design.theme.LocalMeasureTheme

@Composable
fun CTRadioButton(label: String, selected: Boolean, enabled: Boolean = true, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {

        RadioButton(selected = selected,enabled = enabled, onClick = { onClick.invoke() })

        CTText(text = label)

        CTAddSpace(LocalMeasureTheme.current.marginHorizontalShort, ModeAlignmentDirection.HORIZONTAL)
    }
}