package com.example.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import imagedb.ImageDatabase

class InMemoryDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        ImageDatabase.Schema.create(driver)
        return driver
    }

    companion object {
        fun createDatabase(): ImageDatabase {
            val driver = InMemoryDatabaseDriverFactory().createDriver()
            return ImageDatabase(driver)
        }
    }
}