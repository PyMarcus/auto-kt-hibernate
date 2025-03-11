package org.example.service

import org.example.repository.DriverRepository
import java.io.File

object DriverService {
    private val xlsxFile  = this::class.java.classLoader.getResourceAsStream("motoristas.xlsx")
    private val repository: DriverRepository = DriverRepository()

    fun readXlsxFile(){
        xlsxFile.use {
            repository.saveList(it)
        }
    }
}