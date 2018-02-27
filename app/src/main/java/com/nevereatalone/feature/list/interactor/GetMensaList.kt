package com.nevereatalone.feature.list.interactor

import com.nevereatalone.feature.models.Canteen
import io.reactivex.Single
import io.reactivex.Single.defer
import io.reactivex.Single.just
import javax.inject.Inject


interface GetMensaList {
    fun call(): Single<List<Canteen>>
}

class GetMensaListImpl @Inject constructor() : GetMensaList {
    // inject repository here to fetch real canteen
    override fun call(): Single<List<Canteen>> =
            defer { just(generateCanteen()) }


    private fun generateCanteen(): List<Canteen> =
            listOf(
                    Canteen("Jodel canteen", "WilheimStr 118, 10117, Berlin"),
                    Canteen("Mensa UniCampus Magdeburg", "Pfälzer Str. 1, 39106 Magdeburg"),
                    Canteen("Bistro Tasty Studio Babelsberg", "August-Bebel-Str. 26-53, 14482 Potsdam, Deutschland"),
                    Canteen("Chunyuan's Kitchen", "Katzbach Str 4, 21111, China"),
                    Canteen("Patrick's fancy pizza", "Somewhere on the earth, 10000, Berlin"),
                    Canteen("Frodo's kitchen", "Bag end")
            )
}