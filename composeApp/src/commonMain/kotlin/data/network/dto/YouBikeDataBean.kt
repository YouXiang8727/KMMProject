package data.network.dto

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
    fun toYouBikeData(): YouBikeData =
        YouBikeData(
            sno = sno,
            sna = sna,
            sarea = sarea,
            mday = mday,
            ar = ar,
            sareaen = sareaen,
            snaen = snaen,
            aren = aren,
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
