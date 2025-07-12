package com.example.cineko.components

import android.accounts.AuthenticatorDescription
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.cineko.R
import com.example.cineko.ui.theme.Black100
import com.example.cineko.ui.theme.Blue900
import com.example.cineko.ui.theme.Red200
import com.example.cineko.ui.theme.Red500
import com.example.cineko.ui.theme.Red700
import com.example.cineko.ui.theme.Red900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    icon: Int = R.drawable.ic_username,
    iconContentDescription: String? = null

){

    TextField(
        value,
        onValueChange,
        modifier,
        label = {
            Text(text = hint, color = Color.Black)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Red900,
            cursorColor = Color.Black
        ),
        maxLines = 1,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 18.sp
        ),
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon) ,
                contentDescription = iconContentDescription,
                tint = Black100
            )
        }


    )

}

@Composable
@Preview
private fun TextFieldCustomPreview(){

    var username by remember{
        mutableStateOf("")
    }

    TextFieldCustom(
        value = username,
        onValueChange = {
            username = it
        },
        hint = "Username",
        icon = R.drawable.ic_username,
        iconContentDescription = "Icon Username"
    )
}