package com.example.danp_lab03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crud_jetpack_compose.Asistente
import com.example.danp_lab03.ui.theme.DANP_LAB03Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            DANP_LAB03Theme {

                val listaAsistentes = remember { mutableStateListOf<Asistente>() }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ScreenCRUD(listaAsistentes)
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenCRUD(listaAsistentes: MutableList<Asistente>) {

    var nombre by remember { mutableStateOf("") }
    var fechaIncripcion by remember { mutableStateOf("") }
    var tipoSangre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var montoPagado by remember { mutableStateOf("") }
    var isEditando by remember { mutableStateOf(false) }
    var textButton by remember { mutableStateOf("Agregar Asistente") }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(12.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Formulario(
                nombre = nombre,
                funNombre = { nombre = it },
                fechaInscripcion = fechaIncripcion,
                funFechaInscripcion = { fechaIncripcion = it },
                tipoSangre = tipoSangre,
                funTipoSangre = { tipoSangre = it },
                telefono = telefono,
                funTelefono = { telefono = it },
                correo = correo,
                funCorreo = { correo = it },
                montoPagado = montoPagado,
                funMontoPagado = { montoPagado = it },
                isEditando = isEditando,
                funIsEditando = { isEditando = false },
                textButton = textButton,
                funTextButton = { textButton = it },
                listaAsistentes = listaAsistentes,
                funResetCampos = {
                    nombre = ""
                    fechaIncripcion = ""
                    tipoSangre = ""
                    telefono = ""
                    correo = ""
                    montoPagado = ""
                }
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(listaAsistentes) { asistente ->

                        CardAsistente(
                            funNombre = { nombre = it },
                            funFechaInscripcion =  { fechaIncripcion = it },
                            funTipoSangre =  { tipoSangre = it },
                            funTelefono =  { telefono = it },
                            funCorreo =  { correo = it },
                            funMontoPagado =  { montoPagado = it },
                            asistente = asistente,
                            funTextButton = { textButton = it },
                            funIsEditando = { isEditando = it },
                            funBorrarAsistentes = { borrarAsistente(it, listaAsistentes) }
                        )
                    }
                }
            }
        }
    }
}

fun agregarAsistente(nombre: String, fechaInscripcion: String, tipoSangre:String, telefono:String, correo:String, montoPagado:String, listaUsuarios: MutableList<Asistente>) {
    listaUsuarios.add(Asistente(nombre,fechaInscripcion,tipoSangre,telefono,correo,montoPagado))
}

fun editarAsistente(nombre: String, fechaInscripcion: String, tipoSangre:String, telefono:String, correo:String, montoPagado:String, listaAsistentes: MutableList<Asistente>) {
    listaAsistentes.forEach { usuario ->
        if (usuario.nombre == nombre) {
            usuario.fechaInscripcion = fechaInscripcion
            usuario.tipoSangre = tipoSangre
            usuario.telefono = telefono
            usuario.correo = correo
            usuario.montoPagado = montoPagado
        }
    }
}

fun borrarAsistente(nombre: String, listaAsistentes: MutableList<Asistente>) {
    listaAsistentes.forEach { asistente ->
        if (asistente.nombre == nombre) {
            listaAsistentes.remove(asistente)
        }
    }
}