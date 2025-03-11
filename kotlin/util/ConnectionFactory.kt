package org.example.util

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object ConnectionFactory {
    private val sessionPostgresFactory: SessionFactory = buildSessionFactory()

    private fun buildSessionFactory(): SessionFactory{
        return try {
            Configuration().configure().buildSessionFactory()
        }catch (ex: Throwable){
            throw ExceptionInInitializerError("Fail to create connection ${ex.message}")
        }
    }

    fun getPostgresSessionFactory(): SessionFactory = sessionPostgresFactory
}