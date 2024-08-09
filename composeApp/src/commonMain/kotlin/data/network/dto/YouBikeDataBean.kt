package data.network.dto

import domain.model.Language
import domain.model.YouBikeData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YouBikeDataBean(
    @SerialName("sno")
    val sno: String,
    @SerialName("sna")
    val sna: String,
    @SerialName("sarea")
    val sarea: String,
    @SerialName("mday")
    val mday: String,
    @SerialName("ar")
    val ar: String,
    @SerialName("sareaen")
    val sareaen: String,
    @SerialName("snaen")
    val snaen: String,
    @SerialName("aren")
    val aren: String,
    @SerialName("act")
    val act: String,
    @SerialName("srcUpdateTime")
    val srcUpdateTime: String,
    @SerialName("updateTime")
    val updateTime: String,
    @SerialName("infoTime")
    val infoTime: String,
    @SerialName("infoDate")
    val infoDate: String,
    @SerialName("total")
    val total: Int,
    @SerialName("available_rent_bikes")
    val available_rent_bikes: Int,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("available_return_bikes")
    val available_return_bikes: Int
) {
    private fun area(language: Language): String = when (language) {
        Language.CN -> sarea
        Language.EN -> sareaen
    }

    private fun na(language: Language): String = when (language) {
        Language.CN -> sna
        Language.EN -> snaen
    }

    private fun ar(language: Language): String = when (language) {
        Language.CN -> ar
        Language.EN -> aren
    }
    fun toYouBikeData(
        language: Language
    ): YouBikeData =
        YouBikeData(
            sno = sno,
            sna = na(language),
            sarea = area(language),
            mday = mday,
            ar = ar(language),
            act = act,
            srcUpdateTime = srcUpdateTime,
            updateTime = updateTime,
            infoTime = infoTime,
            infoDate = infoDate,
            total = total,
            available_rent_bikes = available_rent_bikes,
            latitude = latitude,
            longitude = longitude,
            available_return_bikes = available_return_bikes
        )
}
