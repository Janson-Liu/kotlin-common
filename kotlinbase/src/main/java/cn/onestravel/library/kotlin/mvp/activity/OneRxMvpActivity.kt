package cn.onestravel.library.kotlin.mvp.activity

import android.os.Bundle
import cn.onestravel.library.kotlin.mvp.presenter.OneMvpPresenter
import cn.onestravel.library.kotlin.mvp.view.OneMvpView
import cn.onestravel.library.kotlin.rxrequest.activity.OneRxActivity

/**
 * @name  OneMvpActivity
 * @description 所有Mvp架构的 采用RxJava+Retrofit 请求的Activity 的基类
 * @createTime 2018/12/12 17:00
 * @author onestravel
 * @version 1.0.0
 */
abstract class OneRxMvpActivity<V : OneMvpView, P : OneMvpPresenter<V>> : OneRxActivity(), OneMvpView {
    private val presenter by lazy { createPresenter() }

    protected abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (presenter == null) {
            throw  NullPointerException("Presenter is null! Do you return null in createPresenter()?")
        }
        presenter.onMvpAttachView(this as V, savedInstanceState)
    }


    override fun onStart() {
        super.onStart()
        presenter.let {
            it.onMvpStart()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.let {
            it.onMvpResume()
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.let {
            it.onMvpPause()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.let {
            it.onMvpStop()
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        presenter.let {
            it.onMvpSaveInstanceState(outState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.let {
            it.onMvpDetachView(false)
            it.onMvpDestroy()
        }
    }


}