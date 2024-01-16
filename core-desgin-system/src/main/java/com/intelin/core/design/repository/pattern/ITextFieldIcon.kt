package com.intelin.core.design.repository.pattern

import androidx.compose.runtime.Composable

interface ITextFieldIcon {
    fun leadingIcon(icon: @Composable () -> Unit): ITextFieldIcon
    fun trailingIcon(icon: @Composable () -> Unit): ITextFieldIcon

    fun getLeadingIcon(): @Composable () -> Unit
    fun getTrailingIcon(): @Composable () -> Unit

    companion object : ITextFieldIcon {
        private val default: TextFieldIconImpl
            get() = TextFieldIconImpl()

        override fun leadingIcon(icon: @Composable () -> Unit): ITextFieldIcon {
            return default.leadingIcon(icon)
        }

        override fun trailingIcon(icon: @Composable () -> Unit): ITextFieldIcon {
            return default.trailingIcon(icon)
        }

        override fun getLeadingIcon(): @Composable () -> Unit {
            return default.getLeadingIcon()
        }

        override fun getTrailingIcon(): @Composable () -> Unit {
            return default.getTrailingIcon()
        }
    }
}

private class TextFieldIconImpl: ITextFieldIcon {
    private var leadingIcon: @Composable () -> Unit = {}
    private var trailingIcon: @Composable () -> Unit = {}

    override fun leadingIcon (icon: @Composable () -> Unit): TextFieldIconImpl {
        leadingIcon = icon
        return this
    }

    override fun trailingIcon(icon: @Composable () -> Unit): TextFieldIconImpl {
        trailingIcon = icon
        return this
    }

    override fun getLeadingIcon(): @Composable () -> Unit {
        return leadingIcon
    }

    override fun getTrailingIcon(): @Composable () -> Unit {
       return trailingIcon
    }

}

