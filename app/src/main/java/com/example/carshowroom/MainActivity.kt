package com.example.carshowroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carshowroom.data.Car
import com.example.carshowroom.data.cars
import com.example.carshowroom.ui.theme.CarShowroomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarShowroomTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CarShowroomApp()
                }
            }
        }
    }
}

@Composable
fun CarShowroomApp() {
    Scaffold(
        topBar = { CarShowroomTopAppBar() }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(cars) { car ->
                CarItem(
                    car = car,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun CarItem(
    car: Car,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var currentImageIndex by remember { mutableStateOf(0) } // State untuk menyimpan indeks gambar saat ini

    Card(
        modifier = modifier.animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                CarIcon(car.imageResourceId)
                CarInformation(car.name,car.price,car.features)
                Spacer(Modifier.weight(1f))
                CarItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium)) // Padding horizontal agar lebih rapi
                ) {
                    Text(
                        text = stringResource(R.string.car_detail_content_description),
                        fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.car_detail_text_size).toSp() },
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center // Tempatkan gambar di tengah secara horizontal
                ) {

                // Tampilkan gambar dengan indeks saat ini
                Image(
                    painter = painterResource(car.detailImages[currentImageIndex]), // Ganti detailImageResourceId dengan array gambar
                    contentDescription = "Detail Mobil",
                    modifier = Modifier
                        .size(350.dp, 400.dp)
                        .padding(dimensionResource(R.dimen.paddinglist_large))
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop
                )
            }
                // Tambahkan tombol Previous dan Next
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        // Jika indeks saat ini > 0, kurangi 1, jika tidak tetap di indeks terakhir
                        if (currentImageIndex > 0) currentImageIndex-- else currentImageIndex = car.detailImages.size - 1
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Previous Image")
                    }
                    IconButton(onClick = {
                        // Jika indeks saat ini < ukuran array gambar - 1, tambah 1, jika tidak reset ke 0
                        if (currentImageIndex < car.detailImages.size - 1) currentImageIndex++ else currentImageIndex = 0
                    }) {
                        Icon(Icons.Filled.ArrowForward, contentDescription = "Next Image")
                    }
                }
            }
        }
    }
}


@Composable
fun CarItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun CarShowroomTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.logomobil), // Logo showroom
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun CarIcon(
    @DrawableRes carIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(carIcon),
        contentDescription = null
    )
}

@Composable
fun CarInformation(
    name: String,
    price: String,
    features: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.text_size).toSp() }
            ),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )

        // Harga mobil
        Text(
            text = price,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.small_text).toSp() }
            ),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_extra_small))
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

        // Daftar fitur mobil tanpa ikon
        features.forEach { feature ->
            Text(
                text = feature,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.extra_small_text).toSp() }
                ),
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_extra_small))
            )
        }
    }
}




@Preview
@Composable
fun CarShowroomPreview() {
    CarShowroomTheme(darkTheme = false) {
        CarShowroomApp()
    }
}

@Preview
@Composable
fun CarShowroomDarkThemePreview() {
    CarShowroomTheme(darkTheme = true) {
        CarShowroomApp()
    }
}