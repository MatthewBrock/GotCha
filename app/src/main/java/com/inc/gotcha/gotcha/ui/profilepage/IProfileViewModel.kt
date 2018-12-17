package com.inc.gotcha.gotcha.ui.profilepage

interface IProfileViewModel {
    fun getFieldVMs(): ArrayList<IProfileFieldViewModel>
    fun save(type: String?, mediaString: String?)
}