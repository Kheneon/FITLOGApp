package com.example.fitlogapp

import java.util.concurrent.Executor
import java.util.concurrent.Executors

// Jednoduchý Executor pro spuštění IO operací na jiném vlákně
private val IO_EXECUTOR: Executor = Executors.newSingleThreadExecutor()

// Funkce, která provede zadaný blok kódu ve vlákně pro IO operace
fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}
