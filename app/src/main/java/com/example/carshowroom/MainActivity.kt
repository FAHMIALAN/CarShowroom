// Package utama dari aplikasi showroom mobil
package com.example.carshowroom

// Mengimpor kelas dan komponen Android yang dibutuhkan
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

// Kelas utama MainActivity untuk menampilkan konten aplikasi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // Fungsi utama yang dijalankan saat aplikasi dimulai
        super.onCreate(savedInstanceState)
        setContent {
            // Menetapkan tema aplikasi
            CarShowroomTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize() // Mengisi seluruh layar
                ) {
                    CarShowroomApp() // Memanggil fungsi utama aplikasi showroom
                }
            }
        }
    }
}

// Fungsi utama untuk mengatur tata letak aplikasi showroom mobil
@Composable
fun CarShowroomApp() {
    Scaffold(
        topBar = { CarShowroomTopAppBar() } // Menyertakan top app bar
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) { // Menampilkan daftar mobil dalam bentuk kolom
            items(cars) { car ->
                CarItem(
                    car = car, // Menampilkan setiap item mobil
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

// Fungsi untuk menampilkan detail setiap item mobil dalam daftar
@Composable
fun CarItem(
    car: Car,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) } // Variabel untuk menyimpan status tampilan detail
    var currentImageIndex by remember { mutableStateOf(0) } // Indeks gambar saat ini pada detail

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
                    .fillMaxWidth() // Mengisi lebar penuh
                    .padding(dimensionResource(R.dimen.padding_small)) // Menambah padding
            ) {
                CarIcon(car.imageResourceId) // Menampilkan ikon mobil
                CarInformation(car.name, car.price, car.features) // Informasi detail mobil
                Spacer(Modifier.weight(1f)) // Memberi jarak antar elemen
                CarItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded } // Membuka atau menutup detail item
                )
            }
            if (expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.padding_medium)) // Padding horizontal
                ) {
                    Text(
                        text = stringResource(R.string.car_detail_content_description), // Deskripsi
                        fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.car_detail_text_size).toSp() },
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(), // Menyelaraskan gambar mobil
                    contentAlignment = Alignment.Center
                ) {
                    // Menampilkan gambar berdasarkan indeks
                    Image(
                        painter = painterResource(car.detailImages[currentImageIndex]),
                        contentDescription = "Detail Mobil",
                        modifier = Modifier
                            .size(350.dp, 400.dp) // Ukuran gambar
                            .padding(dimensionResource(R.dimen.paddinglist_large))
                            .clip(MaterialTheme.shapes.small), // Bentuk klip
                        contentScale = ContentScale.Crop
                    )
                }
                // Baris untuk menampilkan tombol Previous dan Next
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        // Pindah ke gambar sebelumnya
                        if (currentImageIndex > 0) currentImageIndex-- else currentImageIndex = car.detailImages.size - 1
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Previous Image")
                    }
                    IconButton(onClick = {
                        // Pindah ke gambar berikutnya
                        if (currentImageIndex < car.detailImages.size - 1) currentImageIndex++ else currentImageIndex = 0
                    }) {
                        Icon(Icons.Filled.ArrowForward, contentDescription = "Next Image")
                    }
                }
            }
        }
    }
}

// Fungsi tombol untuk membuka atau menutup tampilan detail
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
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

// Fungsi untuk menampilkan AppBar di atas aplikasi showroom
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

// Fungsi untuk menampilkan ikon mobil kecil pada setiap item
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

// Fungsi untuk menampilkan informasi nama, harga, dan fitur mobil
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

        // Menampilkan harga mobil
        Text(
            text = price,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.small_text).toSp() }
            ),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_extra_small))
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

        // Menampilkan daftar fitur mobil
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

// Fungsi untuk melihat pratinjau aplikasi dengan tema default
@Preview
@Composable
fun CarShowroomPreview() {
    CarShowroomTheme(darkTheme = false) {
        CarShowroomApp()
    }
}

// Fungsi untuk melihat pratinjau aplikasi dengan tema gelap
@Preview
@Composable
fun CarShowroomDarkThemePreview() {
    CarShowroomTheme(darkTheme = true) {
        CarShowroomApp()
    }
}
