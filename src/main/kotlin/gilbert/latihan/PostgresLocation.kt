package gilbert.latihan

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.transactions.transaction
class PostgresLocation {
    object Locations : Table("location_database") {
        val id = integer("id")
        val city = varchar("city", 50)
        val province = varchar("province", 50)
        val description = varchar("description", 50)
    }

    fun getLocations() : ArrayList<Location> {
        var locations: ArrayList<Location> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for (location in Locations.selectAll()){
                locations.add(Location( location[Locations.province],
                                        location[Locations.description],
                                        location[Locations.city]))
            }
        }
        return locations
    }

    fun getLocationById(id: Int) : Location?{
        var out: Location? = null
        transaction {
            PostgresConnect().connect()
            for(location in Locations.selectAll()){
                if (location[Locations.id] == id){
                    out = Location( location[Locations.province],
                                    location[Locations.description],
                                    location[Locations.city])
                }
            }
        }
        return out
    }
}