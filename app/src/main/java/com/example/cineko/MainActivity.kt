package com.example.cineko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cineko.list_item.CatItem
import com.example.cineko.model.Catalog


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        CatList()
            }
        }
    }

@Composable
private fun CatList(){
    val catList: MutableList<Catalog> = mutableListOf(
        Catalog(
            imgCat = R.drawable.movie1,
            movName = "Bad Boys IV",
            movDescription = "Os brincalhões Mike Lowrey e Marcus Burnett, em uma perigosa missão para limpar o nome do falecido capitão.",
            parRating = "+14"
        ),
        Catalog(
            imgCat = R.drawable.movie2,
            movName = "Divertida Mente 2",
            movDescription = "Com um salto temporal, Riley se encontra mais velha, passando pela tão temida adolescência.",
            parRating = "+10"
        ),
        Catalog(
            imgCat = R.drawable.movie3,
            movName = "Garfield",
            movDescription = "Garfield tem um reencontro inesperado com seu pai, que estava há muito tempo desaparecido.",
            parRating = "L"
        ),
        Catalog(
            imgCat = R.drawable.movie4,
            movName = "Malandro",
            movDescription = "O comediante Mallandro passa por uma fase ruim. O dinheiro está cada vez mais curto e ele até tenta trabalhar como Uber.",
            parRating = "+12"
        ),
        Catalog(
            imgCat = R.drawable.movie5,
            movName = "Planeta dos Macacos: O Reinado",
            movDescription = "Muitas sociedades cresceram desde quando César levou seu povo a um oásis, enquanto os humanos foram reduzidos a sobreviver.",
            parRating = "+16"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)

    ){
    LazyColumn{
        itemsIndexed(catList){position,catalog ->
            CatItem(catalog)

        }
      }
    }
}