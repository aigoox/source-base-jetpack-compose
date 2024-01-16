package com.intelin.core.design.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.intelin.core.design.R
import com.intelin.core.design.component.layout.CTAddSpace
import com.intelin.core.design.component.layout.CTRowLayout
import com.intelin.core.design.component.text.CTText
import com.intelin.core.design.component.text.CTTextLarge
import com.intelin.core.design.component.text.CTTextSmall
import com.intelin.core.design.component.text.CTTextStyleLarge
import com.intelin.core.design.repository.interfaces.IPropsOTPScreen
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.censoring
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.width
import com.intelin.core.library.ultils.AppUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CTOtpItem(focusRequest: FocusRequester?,
                      width: Dp = AppUtils.widthScreen(0.12f),
                      onValueChange: (String) -> Unit) {
    var valueItem by remember { mutableStateOf ("") }
    val keyboard = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    )
    OutlinedTextField(
        value = valueItem,
        textStyle = TextStyle(
            fontSize = LocalMeasureTheme.current.sizeTextSmall,
            textAlign = TextAlign.Center
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        onValueChange = {
            if (it.isEmpty()) {
                valueItem = ""
                onValueChange("")
                return@OutlinedTextField
            }
            if (it.toIntOrNull().isNotNull()) {
                val value = it.last().toString()
                valueItem = value
                onValueChange(value)
            }
        },
        placeholder = {
            CTTextSmall(text = "*", textAlign = TextAlign.Center)
        },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .focusRequester(focusRequest!!).width(width).padding(0.dp),
        keyboardOptions = keyboard,
    )
}
@Composable
fun CTOtp(countItem: Int = 6, onValueChange: (String) ->Unit) {
    val valueOTP by remember {
        mutableStateOf(Array(countItem) {""})
    }
    val focusRequesters = List(countItem) { remember { FocusRequester() } }
    CTRowLayout(arrangementHorizontal = Arrangement.SpaceAround) {
        valueOTP.mapIndexed { index, _ ->
            CTOtpItem(focusRequest = focusRequesters[index]) {
                valueOTP[index] = it

                onValueChange.invoke(valueOTP.joinToString(""))

                if (index < countItem - 1) {
                    focusRequesters[index + 1].requestFocus()
                }
            }
        }
    }
}

@Composable
fun CTOtpScreen(
    props: IPropsOTPScreen
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        CTText(text = stringResource(R.string.label_verify_otp_code), style = MaterialTheme.typography.titleLarge)

        CTAddSpace(LocalMeasureTheme.current.marginVerticalShort)

        CTTextSmall(text = stringResource(id = R.string.content_note_register_otp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(LocalMeasureTheme.current.paddingHorizontalBody, 0.dp))

        CTAddSpace(LocalMeasureTheme.current.marginVerticalShort)

        CTTextLarge(text = props.phone().censoring(3, 3, 'x'),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(LocalMeasureTheme.current.paddingHorizontalBody, 0.dp))

        CTAddSpace()

        CTOtp(onValueChange = { props.onChangeOtp(it) })

        CTAddSpace()

        CTTextSmall(text = stringResource(id = R.string.label_did_not_receive_otp))

        CTAddSpace()

        CTTextStyleLarge(text = stringResource(id = R.string.label_resend_code),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            onClick = { props.handleResendOtp() }
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun RenderOtpScreenPreview() {
    CTOtpScreen(object: IPropsOTPScreen{
        override fun onChangeOtp(data: String) {
            // preview
        }

        override fun handleResendOtp() {
            // preview
        }

        @Composable
        override fun phone(): String {
           return "3525235"
        }

    })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OtpPreview(){
    CTOtp {}
}

@Preview(showBackground = true)
@Composable
private fun CTOtpItemPreview() {
    CTOtpItem(null){}
}