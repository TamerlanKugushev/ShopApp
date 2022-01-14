package com.example.shopapp.di

import com.example.shopapp.data.datasource.RemoteDataSource
import com.example.shopapp.data.datasource.RemoteDataSourceImpl
import com.example.shopapp.data.imageloader.GlideImageLoader
import com.example.shopapp.data.imageloader.ImageLoader
import com.example.shopapp.data.repository.MainRepository
import com.example.shopapp.data.repository.MainRepositoryImpl
import com.example.shopapp.domain.usecases.MainScreenUseCaseImpl
import com.example.shopapp.domain.usecases.MainScreenUseCase
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}

val interactorModule = module {
    single<MainScreenUseCase> { MainScreenUseCaseImpl(get()) }
}

val imageModule = module {
    single<ImageLoader> { GlideImageLoader() }
}