package ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.network.dto.YouBikeDataBean
import domain.model.YouBikeData

@Composable
fun YouBikeDataItem(
    youBikeData: YouBikeData
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text("(${youBikeData.sno})${youBikeData.sna}")
            Text("(${youBikeData.sarea})${youBikeData.ar}")
            Text("Available/Total: ${youBikeData.available_rent_bikes}/${youBikeData.total}")
            Text("Update Time: ${youBikeData.srcUpdateTime}")
        }
    }
}