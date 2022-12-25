package com.zx_tole.appwithcolorpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.ImageColorPicker
import com.github.skydoves.colorpicker.compose.PaletteContentScale
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.zx_tole.appwithcolorpicker.ui.theme.AppWithColorPickerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppWithColorPickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .width(500.dp)
                        .height(500.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Picker()
                }
            }
        }
    }

    @Composable
    fun Picker() {
        var color: Color = Color.Black

        val controller = rememberColorPickerController()
        ImageColorPicker(
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .padding(top = 40.dp),
            paletteImageBitmap = ImageBitmap.imageResource(id = R.drawable.terapevt_meme),
            controller = controller,
            paletteContentScale = PaletteContentScale.FIT,

            onColorChanged = { colorEnvelope: ColorEnvelope ->
                color = colorEnvelope.color
                setContent {
                    changeTextColor(color = color)
                    Picker()
                }
            }
        )

        controller.selectedColor.value
    }

    @Composable
    fun changeTextColor(color: Color) {
            TextExample(color = color)
    }

    @Composable
    fun TextExample(color: Color) {
        Text(
            text = "Пример цвета",
            modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp),
            color = color
        )
    }
}