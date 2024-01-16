package com.intelin.core.design.component.input

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.intelin.core.design.R
import com.intelin.core.design.component.layout.CTAddSpace
import com.intelin.core.design.component.icon.CTIcon
import com.intelin.core.design.component.text.CTText
import com.intelin.core.design.component.text.CTTextSmall
import com.intelin.core.design.repository.data.ModeAlignmentDirection
import com.intelin.core.design.repository.data.TypeInputField
import com.intelin.core.design.repository.pattern.IStyleTextField
import com.intelin.core.design.repository.pattern.ITextField
import com.intelin.core.design.repository.pattern.ITextFieldIcon
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.compareFalse
import com.intelin.core.library.extension.compareTrue
import com.intelin.core.library.extension.hasVietnameseAccent
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.switch
import com.intelin.core.library.extension.useWhen

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTTextField(
    icon: ITextFieldIcon = ITextFieldIcon,
    text: ITextField = ITextField,
    styleTextField: IStyleTextField = IStyleTextField,
    keyboardOptions: ImeAction = ImeAction.Done,
    onChange: (String) -> Unit = {}) {
    var localValue by remember {
        mutableStateOf(text.getDefaultValue() ?: "")
    }
    var keyboard = KeyboardOptions.Default.copy(
        imeAction = keyboardOptions
    )
    if (styleTextField.getType() == TypeInputField.NUMBER) {
        keyboard = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = keyboardOptions
        )
    }
    Column {
        OutlinedTextField(
            isError = text.getMessageError()?.isNotEmpty().compareTrue(),
            modifier = Modifier
                .fillMaxWidth()
                .useWhen(styleTextField.getModifier().isNotNull(), styleTextField.getModifier()),
            value = localValue,
            singleLine = !styleTextField.getMultipleLine(),
            onValueChange = {

                handleValueChange(it, styleTextField) { value ->
                    localValue = value
                    onChange.invoke(value)
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            label = {

                Row {
                    Text(
                        style = MaterialTheme.typography.labelMedium,
                        text = text.getLabel())
                    CTAddSpace(LocalMeasureTheme.current.marginHorizontalLowShort, ModeAlignmentDirection.HORIZONTAL)
                    if (styleTextField.getRequired()) {
                        CTText(text = "*", color = Color.Red)
                    }
                }
            },
            placeholder = { CTTextSmall(text = text.getPlaceholder().switch(stringResource(id = R.string.placeholder_text_field, text.getLabel().lowercase()))) },
            leadingIcon = icon.getLeadingIcon(),
            trailingIcon = icon.getTrailingIcon(),
            keyboardOptions = keyboard
        )
        if (text.getMessageError()?.isNotEmpty().compareTrue()) {
            CTText(text = text.getMessageError()!!, color = MaterialTheme.colorScheme.error)
        }
    }

}

private fun handleValueChange(_value: String, styleTextField: IStyleTextField, onChange: (String) -> Unit) {
    if (_value != " " || styleTextField.getType() == TypeInputField.TEXT) {
        val value = if (styleTextField.getType() == TypeInputField.TEXT && styleTextField.getNotSpace().compareFalse()) _value else _value.trim()
        if (styleTextField.getUseSpecialChar()) {
            handleValueChangeUseSpecialChar(value, styleTextField) { onChange.invoke(it) }
        }else {
            handleValueChangeNotUseSpecialChar(value, styleTextField) { onChange.invoke(it) }
        }
    }
}

private fun handleValueChangeUseSpecialChar(_value: String, styleTextField: IStyleTextField, onChange: (String) -> Unit) {
    if ((styleTextField.getEnglish() && _value.hasVietnameseAccent().compareFalse()) || styleTextField.getEnglish().compareFalse()) {
        onChange.invoke(_value)
    }
}
private fun handleValueChangeNotUseSpecialChar(_value: String, styleTextField: IStyleTextField, onChange: (String) -> Unit) {
    if ((styleTextField.getEnglish() && _value.hasVietnameseAccent().compareFalse()) || styleTextField.getEnglish().compareFalse()) {
        if (_value.isNotEmpty()) {
            val digit = _value.last().code
            if (digit in 48..57 || digit in 65..95 || digit in 97..122 || digit == 32) {
                onChange.invoke(_value)
            }
        }else{
            onChange.invoke("")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CTTextFieldCommonPreview() {
    CTTextField(
        text = ITextField.label("Text Field"),
        icon = ITextFieldIcon
            .leadingIcon { CTIcon(Icons.Default.Person) }
            .trailingIcon { CTIcon(Icons.Default.Person) }
    )
}
