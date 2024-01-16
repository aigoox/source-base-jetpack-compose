package com.intelin.core.library.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.Dp

/**
 * Click and use Ripple
 * hiddenKeyboard = true - click and hidden Keyboard
 * hiddenKeyboard = false - click but don't hidden Keyboard
 */
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableWithoutRipple(hiddenKeyboard: Boolean = false, onClick: () -> Unit): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current
    Modifier.clickable(
        onClick = {
            if (hiddenKeyboard) {
                keyboardController?.hide()
            }
            onClick.invoke()
        },
        indication = null,
        interactionSource = interactionSource
    )
}

/**
 * Click but don't use Ripple
 * hiddenKeyboard = true - click and hidden Keyboard
 * hiddenKeyboard = false - click but don't hidden Keyboard
 * isWithRipple = true - click and use Ripple
 * isWithRipple = false - click but don't use Ripple
 */
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.clickable(hiddenKeyboard: Boolean = true, isWithRipple: Boolean = false, onClick: () -> Unit = {}): Modifier = composed {
    if (isWithRipple) {
        val keyboardController = LocalSoftwareKeyboardController.current
        Modifier.clickable(onClick = {
            if (hiddenKeyboard) {
                keyboardController?.hide()
            }
            onClick.invoke()
        })
    }else {
        clickableWithoutRipple(hiddenKeyboard, onClick)
    }
}

/**
 * Set width value when it is a non-null entity
 */
fun Modifier.width(value: Dp?) = if (value.isNotNull()) { this.width(value!!) } else { this }

/**
 *  Merge 'modifier' field into Modifier owner when 'use' field as True value and 'modifier' field is a non-null entity
 *  Otherwise, return the original value of Modifier owner
 */
fun Modifier.useWhen(use: Boolean = true,modifier: Modifier?) = if (use && modifier.isNotNull()) { this.then(modifier!!) } else { this }

/**
 * Merge 'modifier' field into Modifier owner when 'modifier' field is a non-null entity
 * Otherwise, return the original value of Modifier owner
 */
fun Modifier.useWhen(modifier: Modifier?) = if (modifier.isNotNull()) { this.then(modifier!!) } else { this }

/**
 * Merge 'modifierTrue' field into Modifier owner when 'condition' field as True value
 * Otherwise, merge 'modifierFalse' field into Modifier owner
 * Note: Entities modifier field is Not null, otherwise return the original value of Modifier owner
 */
fun Modifier.useWhenElse(condition: Boolean = true,modifierTrue: Modifier?, modifierFalse: Modifier?): Modifier {
    return if (condition && modifierTrue.isNotNull())
    {
        this.then(modifierTrue!!)
    }
    else {
        if (modifierFalse.isNotNull()) {
            this.then(modifierFalse!!)
        } else {
            this
        }
    }
}

/**
 * Merge field 'modifier' into Modifier owner when field 'modifier' is a non-null entity
 * Otherwise, merge 'modifierReplace' into Modifier owner
 */
fun Modifier.useWhenElse(modifier: Modifier?, modifierReplace: Modifier): Modifier {
    return if (modifier.isNotNull())
    {
        this.then(modifier!!)
    }
    else {
        this.then(modifierReplace)
    }
}