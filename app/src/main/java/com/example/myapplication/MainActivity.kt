package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myapplication.ViewModel.PessoaViewModel
import com.example.myapplication.ViewModel.Repository
import com.example.myapplication.roomDB.Pessoa
import com.example.myapplication.roomDB.PessoaDataBase
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            PessoaDataBase::class.java,
            "pessoa.db"
        ).build()
    }

    private val viewModel by viewModels<PessoaViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PessoaViewModel(Repository(db)) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(viewModel, mainActivity = this)
                }
            }
        }
    }
}

@Composable
fun App(viewModel: PessoaViewModel, mainActivity: MainActivity) {
    var nome = ""
    var idade = ""
    var pessoa = Pessoa( nome,idade  )

    Column(
        Modifier
            .background(Color.White)
            .fillMaxWidth(),
        Arrangement.Center,
    ) {
        Row (
            Modifier
                .padding(20.dp)
        ){

        }

        Row (
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            Text(text = "APP DATABASE",
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)

        }




        Row(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
                Arrangement.Center
        ){
            var nome by remember { mutableStateOf("") }

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("NOME:") } )
        }



        Row (
            Modifier
                .padding(20.dp)
        ){

        }


        Row(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
                Arrangement.Center
        ) {
            var idade by remember { mutableStateOf("") }

            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text("IDADE:") } )
        }



            Row (
                Modifier.padding(25.dp)
                    .fillMaxWidth(),
                        Arrangement.Center
            ){
                Button(
                    onClick = {
                        ViewModel.upsertPessoa(pessoa)
                        nome = ""
                        idade = ""
                    },
                    modifier = Modifier
                        .padding(18.dp),
                    colors =  ButtonDefaults.buttonColors(Color.Black),
                ) {
                    Text(
                        text = "Cadastrar",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,))
                    }
                }
            }

            Row(
                Modifier
                    .padding(20.dp)
            ){

            }

    }


@Preview
@Composable
fun AppPreview() {
    MyApplicationTheme{

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            App()
        }
    }
}