package org.example.repository

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.example.entity.DriverEntity
import org.example.util.ConnectionFactory
import org.hibernate.Session
import java.io.InputStream
import java.util.*

class DriverRepository {
    fun saveList(driverStream: InputStream){
        val workbook = WorkbookFactory.create(driverStream)
        val sheet = workbook.getSheetAt(0)

        val drivers = mutableListOf<DriverEntity>()
        for (row in sheet.drop(1)){
            val name = row.getCell(0).stringCellValue
            val cpf = row.getCell(1).stringCellValue.replace(".", "").replace("-", "")
            val cnh = row.getCell(2).stringCellValue
            val driver = DriverEntity(name, cnh, cpf, cnh, Date())
            drivers.add(driver)
        }

        if (drivers.isNotEmpty()){
            val session: Session = ConnectionFactory.getPostgresSessionFactory().openSession()
            session.beginTransaction()
            drivers.forEach{
                printData(it)
                session.save(it)
            }
            session.transaction.commit()
            session.close()
        }

    }

    private fun printData(it: DriverEntity){
        println("DriverEntity<${it.name} ${it.cnh} ${it.cnh} ${it.cpf} ${it.dateAdd} ${it.groupId} ${it.subgroupId} ${it.accountId} ${it.status}>")
    }

    fun listAll(): List<DriverEntity>{
        val session: Session = ConnectionFactory.getPostgresSessionFactory().openSession()
        val query = session.createQuery("FROM DriverEntity", DriverEntity::class.java)
        query.maxResults = 10
        val drivers: List<DriverEntity> = query.resultList
        session.clear()
        return drivers
    }
}