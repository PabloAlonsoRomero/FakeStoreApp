package com.example.fakestoreapp.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Inicio - Hilo: ${Thread.currentThread().name}")

        val job = launch(Dispatchers.IO) {
            val resultado = descargarDatos()
            println(resultado)
        }

        println("Descargando datos en segundo plano...")
        job.join()

        println("Fin - Hilo: ${Thread.currentThread().name}")
    }

}

suspend fun descargarDatos(): String {
    delay(3000)
    return "Datos descargados exitosamente en hilo ${Thread.currentThread().name}"
}

suspend fun consultaDB() {
    delay(3000)
    println("Consulta a la base de datos")
    println("Termina consulta de base de datos")
}

fun cLaunch() {
    runBlocking {
        // Hacer una consulta a una base de datos
        println("Cargando interfaz grafica")
        launch {
            consultaDB()
        }

        println("Continuo cargando interfaz grafica")
        println("Interfaz cargada")
    }
}

fun cAsync() {
    // SOLO PARA PRUEBAS!!!!!!!!
    runBlocking {
        val result = async {
            println("Haciendo GET a API de FakeStore")
            delay(2000)
            "Datos de la API en JSON { id:Hola }"
        }
        val resultString = result.await()
        println("El resultado es $resultString")
    }
}

