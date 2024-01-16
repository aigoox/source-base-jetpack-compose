package com.intelin.core.design.repository.pattern

interface ITextField {
    fun label(text: String): ITextField
    fun messageError(text: String): ITextField
    fun placeholder(text: String): ITextField
    fun defaultValue(text: String): ITextField

    fun getLabel(): String
    fun getMessageError(): String?
    fun getPlaceholder(): String?
    fun getDefaultValue(): String?

    companion object: ITextField {
        private val default: TextFieldImpl
            get() = TextFieldImpl()

        override fun label(text: String): ITextField {
            return default.label(text)
        }

        override fun messageError(text: String): ITextField {
            return default.messageError(text)
        }

        override fun placeholder(text: String): ITextField {
            return default.placeholder(text)
        }

        override fun defaultValue(text: String): ITextField {
            return default.defaultValue(text)
        }

        override fun getLabel(): String {
            return default.getLabel()
        }

        override fun getMessageError(): String? {
            return default.getMessageError()
        }

        override fun getPlaceholder(): String? {
            return default.getPlaceholder()
        }

        override fun getDefaultValue(): String? {
            return default.getDefaultValue()
        }

    }
}

private class TextFieldImpl: ITextField {
    private var label: String = ""
    private var placeholder: String? = null
    private var messageError: String? = null
    private var defaultValue: String? = null

    override fun label(text: String): ITextField {
        label = text
        return this
    }

    override fun messageError(text: String): ITextField {
        messageError = text
        return this
    }

    override fun placeholder(text: String): ITextField {
        placeholder = text
        return this
    }

    override fun defaultValue(text: String): ITextField {
        defaultValue = text
        return this
    }

    override fun getLabel(): String {
        return label
    }

    override fun getMessageError(): String? {
        return messageError
    }

    override fun getPlaceholder(): String? {
        return placeholder
    }

    override fun getDefaultValue(): String? {
        return defaultValue
    }

}