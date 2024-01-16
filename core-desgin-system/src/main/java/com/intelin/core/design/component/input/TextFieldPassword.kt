package com.intelin.core.design.component.input

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.intelin.core.design.R
import com.intelin.core.design.component.icon.CTIconField
import com.intelin.core.design.component.layout.CTAddSpace
import com.intelin.core.design.component.text.CTText
import com.intelin.core.design.component.text.CTTextSmall
import com.intelin.core.design.repository.data.ModeAlignmentDirection
import com.intelin.core.design.repository.pattern.IStyleTextField
import com.intelin.core.design.repository.pattern.ITextField
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
fun CTTextFieldPassword(
    text: ITextField = ITextField,
    styleTextField: IStyleTextField = IStyleTextField,
    onChange: (String) -> Unit) {

    var isEye: Boolean by remember {
        mutableStateOf(false)
    }

    var localValue by remember {
        mutableStateOf(text.getDefaultValue() ?: "")
    }

    Column {
        OutlinedTextField(
            isError = text.getMessageError()?.isNotEmpty().compareTrue(),
            modifier = Modifier
                .fillMaxWidth()
                .useWhen(
                    styleTextField
                        .getModifier()
                        .isNotNull(), styleTextField.getModifier()
                ),
            value = localValue,
            onValueChange = {
                if (it.hasVietnameseAccent().compareFalse()) {
                    onChange.invoke(it.trim())
                    localValue = it.trim()
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            singleLine = true,
            label = {
                Row {
                    Text(style = MaterialTheme.typography.labelMedium,
                        text = text.getLabel().switch(stringResource(id = R.string.label_password)))

                    CTAddSpace(LocalMeasureTheme.current.marginHorizontalLowShort, ModeAlignmentDirection.HORIZONTAL)

                    if (styleTextField.getRequired()) {
                        CTText(text = "*", color = Color.Red)
                    }
                }
            },
            placeholder = { CTTextSmall(text = text.getPlaceholder()
                .switch(stringResource(R.string.placeholder_text_field,
                        stringResource(R.string.label_password).lowercase()))) },
            leadingIcon = { CTIconField(icon = Icons.Default.Lock) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {
                IconButton(onClick = { isEye = !isEye }) {
                    CTIconField(icon = if (isEye) R.mipmap.ic_show_eye else R.mipmap.ic_hidden_eye)
                }
            },
            visualTransformation = if (isEye) VisualTransformation.None else PasswordVisualTransformation()
        )
        if (text.getMessageError()?.isNotEmpty().compareTrue()) {
            CTText(text = text.getMessageError()!!, color = MaterialTheme.colorScheme.error)
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun CTTextFieldPasswordPreview() {
    CTTextFieldPassword {}
}