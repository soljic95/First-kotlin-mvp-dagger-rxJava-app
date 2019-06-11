package com.example.kotlinfirstapp.dagger.module

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinfirstapp.dagger.qualifiers.ForActivity
import com.example.kotlinfirstapp.dagger.scope.ActivityScope
import com.example.kotlinfirstapp.dagger.scope.FragmentScope
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.main.MainContract
import com.example.kotlinfirstapp.main.MainPresenter
import com.example.kotlinfirstapp.router.Router
import com.example.kotlinfirstapp.router.RouterImpl
import com.example.kotlinfirstapp.ui.loginUser.LoginContract
import com.example.kotlinfirstapp.ui.loginUser.LoginPresenter
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserContract
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserPresenter
import com.example.kotlinfirstapp.ui.splash.SplashContract
import com.example.kotlinfirstapp.ui.splash.SplashPresenter
import com.example.kotlinfirstapp.viewPager.MyViewPagerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ActivityModule(
    private val context: Context,
    private val fragmentManager: FragmentManager
) {

    @Provides
    @ActivityScope
    fun provideActivityContext(): Context {
        return context
    }


    @Provides
    @ActivityScope
    fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }



    @Provides
    @ForActivity
    @ActivityScope
    fun provideFragmentManger(): FragmentManager {
        return fragmentManager
    }

    @Provides
    @ActivityScope
    fun provideViewPagerAdapter(@ForActivity fragmentManager: FragmentManager): MyViewPagerAdapter {
        return MyViewPagerAdapter(fragmentManager)
    }

    @Provides
    @ActivityScope
    fun provideRouter(context: Context, @ForActivity fragmentManager: FragmentManager): Router {
        return RouterImpl(context as Activity, fragmentManager)
    }


    @Provides
    @ActivityScope
    fun provideMainPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router): MainContract.Presenter {
        return MainPresenter(retrofit, coinDao, router)
    }

    @Provides
    @ActivityScope
    fun provideLoginPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router): LoginContract.Presenter {
        return LoginPresenter(retrofit, coinDao, router)
    }

    @Provides
    @ActivityScope
    fun provideRegisterUserPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router)
            : RegisterUserContract.Presenter {
        return RegisterUserPresenter(retrofit, coinDao, router)
    }

    @Provides
    @ActivityScope
    fun provideSplashPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router)
            : SplashContract.Presenter {
        return SplashPresenter(retrofit, coinDao, router)
    }

    interface Exposes {

        fun getRouter(): Router

        fun getLayoutInflater(): LayoutInflater

        fun getContext(): Context
    }
}