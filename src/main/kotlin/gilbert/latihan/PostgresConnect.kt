package gilbert.latihan

import org.jetbrains.exposed.sql.Database

class PostgresConnect {
    fun connect() {
        Database.connect("jdbc:postgresql://localhost:5432/jfood",
                driver = "org.postgresql.Driver",
                user = "postgres",
                password = "postgres")
    }
}