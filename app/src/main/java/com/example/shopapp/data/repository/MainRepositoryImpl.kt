package com.example.shopapp.data.repository

import com.example.shopapp.data.datasource.RemoteDataSource
import com.example.shopapp.data.model.local.BestSeller
import com.example.shopapp.data.model.local.HomeStore
import com.example.shopapp.data.model.local.Main
import com.example.shopapp.data.model.remote.BestSellerResponse
import com.example.shopapp.data.model.remote.HomeStoreResponse
import com.example.shopapp.data.model.remote.MainResponse

class MainRepositoryImpl(private val dataSource: RemoteDataSource) : MainRepository {

    override suspend fun getMain(): Main {
        return convertToMain(dataSource.getMain())
    }

    private fun convertToMain(mainResponse: List<MainResponse>): Main {
        val responseMain = mainResponse[0]
        val homeStore = convertToListHomeStore(responseMain.homeStore)
        val bestSeller = convertToListBestSeller(responseMain.bestSeller)
        return Main(
            id = responseMain.id,
            homeStore = homeStore,
            bestSeller = bestSeller
        )
    }

    private fun convertToListBestSeller(bestSellerResponse: List<BestSellerResponse>): List<BestSeller> {
        return bestSellerResponse.map { responseBestSeller ->
            BestSeller(
                id = responseBestSeller.id,
                isFavorites = responseBestSeller.isFavorites,
                title = responseBestSeller.title,
                priceWithoutDiscount = responseBestSeller.discountPrice,
                discountPrice = responseBestSeller.priceWithoutDiscount,
                picture = responseBestSeller.picture
            )
        }
    }

    private fun convertToListHomeStore(homeStoreResponse: List<HomeStoreResponse>): List<HomeStore> {
        return homeStoreResponse.map { responseHomeStore ->
            HomeStore(
                id = responseHomeStore.id,
                isNew = responseHomeStore.isNew,
                title = responseHomeStore.title,
                subtitle = responseHomeStore.subtitle,
                picture = responseHomeStore.picture
            )
        }
    }

}