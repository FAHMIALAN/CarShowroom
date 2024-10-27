package com.example.carshowroom.data

import com.example.carshowroom.R

data class Car(
    val name: String,
    val price: String,
    val features: List<String>,
    val imageResourceId: Int,        // Gambar utama
    val detailImages: List<Int>      // Daftar gambar detail mobil
)

val cars = listOf(
    Car(
        name = "CIVIC 1.5 TURBO 2020",
        price = "Rp. 460.000.000",
        features = listOf(
            "• Tenaga 171 HP",
            "• DOHC 4 Silinder Inline",
            "• Kapasitas Mesin 1500 cm3"
        ),
        imageResourceId = R.drawable.image1a,
        detailImages = listOf(R.drawable.image1a, R.drawable.image1b, R.drawable.image1c, R.drawable.image1d ,) // Tambahkan beberapa gambar
    ),
    Car(
        name = "TOYOTA SUPRA MK-5",
        price = "Rp. 3.000.000.000",
        features = listOf(
            "• Tenaga 400 HP",
            "• Sylinder 6 Inline",
            "• Kapasitas Mesin 3000 cm3"
        ),
        imageResourceId = R.drawable.image2a,
        detailImages = listOf(R.drawable.image2a, R.drawable.image2b, R.drawable.image2c,R.drawable.image2d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "HONDA CIVIC EG-6",
        price = "Rp. 160.000.000",
        features = listOf(
            "• Tenaga 160 HP",
            "• Sylinder 4 Inline",
            "• Kapasitas Mesin 1600 cm3"
        ),
        imageResourceId = R.drawable.image3a,
        detailImages = listOf(R.drawable.image3a, R.drawable.image3b, R.drawable.image3c,R.drawable.image3d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "TOYOTA SUPRA MK-4",
        price = "Rp. 1.800.000.000",
        features = listOf(
            "• Tenaga 320 HP",
            "• Sylinder 6 Inline",
            "• Kapasitas Mesin 3000 cm3"
        ),
        imageResourceId = R.drawable.image4a,
        detailImages = listOf(R.drawable.image4a, R.drawable.image4b, R.drawable.image4c,R.drawable.image4d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "NISSAN GTR R-34",
        price = "RP. 1.600.000.000",
        features = listOf(
            "• Tenaga 280 HP",
            "• Sylinder 6 Inline",
            "• Kapasitas Mesin 2800 cm3"
        ),
        imageResourceId = R.drawable.image5a,
        detailImages = listOf(R.drawable.image5a, R.drawable.image5b, R.drawable.image5c,R.drawable.image5d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "NISSAN GTR R-35",
        price = "Rp. 3.200.000.000",
        features = listOf(
            "• Sylinder V6",
            "• Tenaga 600 HP",
            "• Kapasitas Mesin 3799 cm3"
        ),
        imageResourceId = R.drawable.image6a,
        detailImages = listOf(R.drawable.image6a, R.drawable.image6b, R.drawable.image6c,R.drawable.image6d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "MAZDA RX-7",
        price = "Rp. 1.800.000.000",
        features = listOf(
            "• Tenaga 250 HP",
            "• Mesin Rotary 13B ",
            "• Kapasitas Mesin 1300 cm3"
        ),
        imageResourceId = R.drawable.image7a,
        detailImages = listOf(R.drawable.image7a, R.drawable.image7b,R.drawable.image7c,R.drawable.image7d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "TOYOTA MK-5",
        price = "Rp. 2.900.000.000",
        features = listOf(
            "• Tenaga 400 HP",
            "• Sylinder 6 Inline",
            "• Kapasitas Mesin 3000 cm3"
        ),
        imageResourceId = R.drawable.image8a,
        detailImages = listOf(R.drawable.image8a, R.drawable.image8b, R.drawable.image8c,R.drawable.image8d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "TOYOTA SUPRA MK-5 ",
        price = "RP. 2.800.000.000",
        features = listOf(
            "• Tenaga 400 HP",
            "• Sylinder 6 Inline",
            "• Kapasitas Mesin 3000 cm3"
        ),
        imageResourceId = R.drawable.image9a,
        detailImages = listOf(R.drawable.image9a, R.drawable.image9b, R.drawable.image9c,R.drawable.image9d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "SUBARU WRX",
        price = "Rp. 1.800.000.000",
        features = listOf(
            "• Tenaga 275 HP",
            "• Sylinder 4 Inline",
            "• Kapasitas Mesin 2400 cm3"
        ),
        imageResourceId = R.drawable.image10a,
        detailImages = listOf(R.drawable.image10a, R.drawable.image10b, R.drawable.image10c,R.drawable.image10d) // Tambahkan beberapa gambar
    ),
    Car(
        name = "LANCER EVO-8",
        price = "Rp. 1.700.000.000",
        features = listOf(
            "• Tenaga 280 HP",
            "• Sylinder 4 Inline",
            "• Kapasitas Mesin 2000 cm3"
        ),
        imageResourceId = R.drawable.image11a,
        detailImages = listOf(R.drawable.image11a, R.drawable.image11b, R.drawable.image11c,R.drawable.image11d) // Tambahkan beberapa gambar
    )
)