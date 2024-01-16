package com.intelin.core.design.component.process

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.intelin.core.design.R
import com.intelin.core.design.component.icon.CTIcon
import com.intelin.core.design.component.image.CTImageIcon
import com.intelin.core.design.component.layout.CTAddDriver
import com.intelin.core.design.component.text.CTTextSmall
import com.intelin.core.design.repository.data.StatusStep
import com.intelin.core.design.repository.model.StepsItemModel
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.useWhen
import com.intelin.core.library.ultils.AppUtils

@Composable
fun CTStepItem(item: StepsItemModel, status: StatusStep = StatusStep.PENDING) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.useWhen(StatusStep.DONE != status,
            Modifier
                .background(
                    shape = RoundedCornerShape(LocalMeasureTheme.current.sizeSquareStandard),
                    color = if (status == StatusStep.PENDING) Color.Gray else Color.Green
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(LocalMeasureTheme.current.sizeSquareStandard),
                    color = Color.Black
                )
                .padding(LocalMeasureTheme.current.paddingFieldShort))) {

            when(status) {
                StatusStep.DONE -> CTImageIcon(R.mipmap.ic_done_step, AppUtils.widthScreen(0.12f))
                else -> CTIcon(item.icon, AppUtils.widthScreen(0.06f))
            }
        }
        item.label?.let { CTTextSmall(text = it, textAlign = TextAlign.Center) }
    }
}

@Composable
fun CTSteps(arrayValue: List<StepsItemModel>, indexProcess: Int = 0, paddingHorizontal: Dp = 0.dp) {
    var indexProcessLocal = indexProcess
    if (indexProcess < 0 || indexProcess >= arrayValue.size) {
        indexProcessLocal = -1
    }
    val contentStepWidth = if (indexProcess > 0) (AppUtils.widthScreen(0.12f) * indexProcess) + (AppUtils.widthScreen(0.06f) * (arrayValue.size - indexProcess))
    else (AppUtils.widthScreen(0.06f) * arrayValue.size)
    val width = (AppUtils.widthScreen() - contentStepWidth - (paddingHorizontal * 3)) / arrayValue.size

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        arrayValue.mapIndexed { index, item ->
            if(indexProcessLocal == -1 || index < indexProcessLocal) {
                CTStepItem(item = item, status = StatusStep.DONE)
            } else if (index == indexProcessLocal) {
                CTStepItem(item = item, status = StatusStep.PROCESS)
            } else {
                CTStepItem(item = item)
            }

            if (index < arrayValue.lastIndex) {

                CTAddDriver(width = width, modifier = Modifier.padding(0.dp, AppUtils.widthScreen(0.12f) / 2))
            }
        }
    }
}

@Composable
fun CTStepRegister(indexProcess: Int = 0) {
    val array = listOf(
        StepsItemModel(Icons.Default.Phone, stringResource(id = R.string.label_phone)),
        StepsItemModel(Icons.Default.Lock, stringResource(id = R.string.label_otp)),
        StepsItemModel(Icons.Default.Person, stringResource(id = R.string.label_information)))
    CTSteps(arrayValue = array, indexProcess = indexProcess, paddingHorizontal = LocalMeasureTheme.current.paddingBody)
}
@Preview(showBackground = true)
@Composable
private fun CTStepsPreview() {
    val array = listOf(
        StepsItemModel(Icons.Default.Phone, stringResource(id = R.string.label_phone)),
        StepsItemModel(Icons.Default.Lock, stringResource(id = R.string.label_otp)),
        StepsItemModel(Icons.Default.Person, stringResource(id = R.string.label_information)))
    CTSteps(array,2)
}

@Preview(showBackground = true)
@Composable
private fun CTStepItemPreview() {
    Column {
        CTStepItem(item = StepsItemModel(Icons.Default.Phone, stringResource(id = R.string.label_phone)))
        CTStepItem(item = StepsItemModel(Icons.Default.Phone, stringResource(id = R.string.label_phone)), status = StatusStep.PROCESS)
        CTStepItem(item = StepsItemModel(Icons.Default.Phone, stringResource(id = R.string.label_phone)), status = StatusStep.DONE)
    }
}