package com.example.elemestest.MVPBase

import io.reactivex.disposables.CompositeDisposable

interface BasePresenter <in T : BaseView> {
    fun onAttach(view: T)
    fun onDeAttach()
}