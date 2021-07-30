package com.example.nikestore.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikestore.R
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class NikeActivity:AppCompatActivity(),NikeView{
    override val rootView: CoordinatorLayout?
        get() = window.decorView.rootView as CoordinatorLayout
    override val viewContext: Context?
        get() = this
}
abstract class NikeFragment:Fragment(),NikeView{
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout?
    override val viewContext: Context?
        get() = context
}

interface NikeView{
    val rootView:CoordinatorLayout?
    val viewContext:Context?
    fun setProgressBar(mustShow:Boolean){
        rootView?.let {
            viewContext?.let { context ->
                var loadingView=it.findViewById<View>(R.id.loadingView)
                if (loadingView==null && mustShow){
                    loadingView=LayoutInflater.from(context).inflate(R.layout.view_loading,it,false)
                    rootView?.addView(loadingView)
                }
                loadingView?.visibility=if (mustShow)View.VISIBLE else View.GONE
            }
        }
    }
}

abstract class NikeViewModel:ViewModel(){
    val progressBarLiveData=MutableLiveData<Boolean>()
    val composDisposable=CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        composDisposable.clear()
    }
}

abstract class NikeSingleObserver<T>(val compositeDisposable: CompositeDisposable):SingleObserver<T>{
    override fun onError(e: Throwable) {
        Timber.e(e)
    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }
}