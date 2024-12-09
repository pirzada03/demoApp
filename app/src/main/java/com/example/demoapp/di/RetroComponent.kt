package com.example.demoapp.di

import com.example.demoapp.viewmodel.TopRatedViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {
    fun inject(topRatedViewModel: TopRatedViewModel)

}