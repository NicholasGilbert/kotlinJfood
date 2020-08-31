package gilbert.latihan

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresSeller {
    object Sellers : Table("seller_database") {
        val id = integer("id")
        val name = varchar("name", 50)
        val email = varchar("email", 50)
        val phone = varchar("phone_number", 20)
        val loc = integer("location_id")
    }

    fun getSellers() : ArrayList<Seller> {
        var sellers: ArrayList<Seller> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for (seller in Sellers.selectAll()){
                sellers.add(Seller( seller[Sellers.id],
                        seller[Sellers.name],
                        seller[Sellers.email],
                        seller[Sellers.phone],
                        PostgresLocation().getLocationById(seller[Sellers.loc])!!))
            }
        }
        return sellers
    }

    fun getSellerById(id: Int) : Seller?{
        var out: Seller? = null
        transaction {
            PostgresConnect().connect()
            for (seller in Sellers.selectAll()){
                if (seller[Sellers.id] == id){
                    out = Seller( seller[Sellers.id],
                            seller[Sellers.name],
                            seller[Sellers.email],
                            seller[Sellers.phone],
                            PostgresLocation().getLocationById(seller[Sellers.loc])!!)
                }
            }
        }
        return out
    }

    fun getLastId() : Int{
        var lastId: Int = 0
        transaction {
            PostgresConnect().connect()
            for(seller in Sellers.slice(Sellers.id).selectAll().orderBy(Sellers.id to SortOrder.DESC).limit(1)) {
                lastId = seller[Sellers.id]
            }
        }
        return lastId
    }

    fun addSeller(inName: String,
                  inEmail: String,
                  inPhone: String,
                  inLocation: Int) : Seller?{
        var regisResult: Seller? = null
        transaction {
            PostgresConnect().connect()
            for(seller in Sellers.selectAll()){
                if (seller[Sellers.email] == inEmail){
                    throw EmailAlreadyExists(inEmail)
                }
            }
            Sellers.insert {
                it[name] = inName
                it[email] = inEmail
                it[phone] = inPhone
                it[loc] = inLocation
            }
            regisResult = getSellerById(getLastId())
        }
        return regisResult
    }
}