package com.intelin.core.design.repository.pattern

interface ITextPopup {
    fun title(text: String): ITextPopup
    fun message(text: String): ITextPopup

    fun getTitle(): String
    fun getMessage(): String

    companion object: ITextPopup {
        private val default: TextPopupImpl
            get() = TextPopupImpl()

        override fun title(text: String): ITextPopup {
            return default.title(text)
        }

        override fun message(text: String): ITextPopup {
            return default.message(text)
        }

        override fun getTitle(): String {
            return default.getTitle()
        }

        override fun getMessage(): String {
            return default.getMessage()
        }


    }
}

private class TextPopupImpl: ITextPopup {
    private var title: String = ""
    private var message: String = ""
    override fun title(text: String): ITextPopup {
        title = text
        return this
    }

    override fun message(text: String): ITextPopup {
        message = text
        return this
    }

    override fun getTitle(): String {
        return title
    }

    override fun getMessage(): String {
        return message
    }

}