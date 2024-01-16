package com.intelin.core.design.repository.pattern

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import com.intelin.core.design.repository.data.TypeInputField

private class StyleTextFieldImpl: IStyleTextField {
    private var isEnglish: Boolean = false
    private var isRequired: Boolean = false
    private var isNotSpace: Boolean = false
    private var isUseSpecialChar: Boolean = true
    private var type: TypeInputField = TypeInputField.TEXT
    private var multipleLine: Boolean = false
    private var modifier: Modifier? = null

    override fun english(): IStyleTextField {
        isEnglish = true
        return this
    }

    override fun required(): IStyleTextField {
        isRequired = true
        return this
    }

    override fun notSpace(): IStyleTextField {
        isNotSpace = true
        return this
    }

    override fun notUseSpecialChar(): IStyleTextField {
        isUseSpecialChar = false
        return this
    }

    override fun typeInputField(type: TypeInputField): IStyleTextField {
        this.type = type
        return this
    }

    override fun multipleLine(): IStyleTextField {
        multipleLine = true
        return this
    }

    override fun modifier(modifier: Modifier): IStyleTextField {
        this.modifier = modifier
        return this
    }

    override fun getEnglish(): Boolean {
        return isEnglish
    }

    override fun getRequired(): Boolean {
        return isRequired
    }

    override fun getNotSpace(): Boolean {
        return isNotSpace
    }

    override fun getUseSpecialChar(): Boolean {
        return isUseSpecialChar
    }

    override fun getType(): TypeInputField {
        return type
    }

    override fun getMultipleLine(): Boolean {
        return multipleLine
    }

    @SuppressLint("ModifierFactoryExtensionFunction")
    override fun getModifier(): Modifier? {
        return modifier
    }
}

interface IStyleTextField {
    fun english(): IStyleTextField
    fun required(): IStyleTextField
    fun notSpace(): IStyleTextField
    fun notUseSpecialChar(): IStyleTextField
    fun typeInputField(type: TypeInputField): IStyleTextField
    fun multipleLine(): IStyleTextField
    fun modifier(modifier: Modifier): IStyleTextField

    fun getEnglish(): Boolean
    fun getRequired(): Boolean
    fun getNotSpace(): Boolean
    fun getUseSpecialChar(): Boolean
    fun getType(): TypeInputField
    fun getMultipleLine(): Boolean
    @SuppressLint("ModifierFactoryExtensionFunction")
    fun getModifier(): Modifier?

    companion object : IStyleTextField {

        private val default: StyleTextFieldImpl
            get() = StyleTextFieldImpl()

        override fun english(): IStyleTextField {
            return default.english()
        }

        override fun required(): IStyleTextField {
            return default.required()
        }

        override fun notSpace(): IStyleTextField {
            return default.notSpace()
        }

        override fun notUseSpecialChar(): IStyleTextField {
            return default.notUseSpecialChar()
        }

        override fun typeInputField(type: TypeInputField): IStyleTextField {
            return default.typeInputField(type)
        }

        override fun multipleLine(): IStyleTextField {
            return default.multipleLine()
        }

        override fun modifier(modifier: Modifier): IStyleTextField {
            return default.modifier(modifier)
        }

        override fun getEnglish(): Boolean {
            return default.getEnglish()
        }

        override fun getRequired(): Boolean {
            return default.getRequired()
        }

        override fun getNotSpace(): Boolean {
            return default.getNotSpace()
        }

        override fun getUseSpecialChar(): Boolean {
            return default.getUseSpecialChar()
        }

        override fun getType(): TypeInputField {
            return default.getType()
        }

        override fun getMultipleLine(): Boolean {
            return default.getMultipleLine()
        }

        @SuppressLint("ModifierFactoryExtensionFunction")
        override fun getModifier(): Modifier? {
            return default.getModifier()
        }

    }
}