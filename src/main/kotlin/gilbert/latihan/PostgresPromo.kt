package gilbert.latihan

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresPromo {
    object Promos : Table("promo_database") {
        val id = Promos.integer("id")
        val code = Promos.varchar("code", 50)
        val discount = Promos.integer("discount")
        val minPrice = Promos.integer("min_price")
        val active = bool("active")
    }

    fun getPromos() : ArrayList<Promo> {
        var promos: ArrayList<Promo> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for (promo in Promos.selectAll()){
                promos.add(Promo(   promo[Promos.id],
                                    promo[Promos.code],
                                    promo[Promos.discount],
                                    promo[Promos.minPrice],
                                    promo[Promos.active]))
            }
        }
        return promos
    }

    fun getLastId() : Int{
        var lastId: Int = 0
        transaction {
            PostgresConnect().connect()
            for(promo in Promos.slice(Promos.id).selectAll().orderBy(Promos.id to SortOrder.DESC).limit(1)) {
                lastId = promo[Promos.id]
            }
        }
        return lastId
    }

    fun getPromoByCode(code: String) : Promo?{
        var out: Promo? = null
        transaction {
            PostgresConnect().connect()
            for (promo in Promos.selectAll()){
                if (promo[Promos.code] == code){
                    out = Promo(promo[Promos.id],
                                promo[Promos.code],
                                promo[Promos.discount],
                                promo[Promos.minPrice],
                                promo[Promos.active])
                }
            }
        }
        return out
    }

    fun getPromoById(id: Int) : Promo?{
        var out: Promo? = null
        transaction {
            PostgresConnect().connect()
            for (promo in Promos.selectAll()){
                if (promo[Promos.id] == id){
                    out = Promo(promo[Promos.id],
                                promo[Promos.code],
                                promo[Promos.discount],
                                promo[Promos.minPrice],
                                promo[Promos.active])
                }
            }
        }
        return out
    }

    fun addPromo(   inCode  :   String,
                    inDis   :   Int,
                    inMin   :   Int,
                    inActive:   Boolean) : Promo?{
        var regisResult: Promo? = null
        transaction {
            PostgresConnect().connect()
            for(promo in Promos.selectAll()){
                if (promo[Promos.code] == inCode){
                    throw PromoCodeAlreadyExists(inCode)
                }
            }
            Promos.insert {
                it[code] = inCode
                it[discount] = inDis
                it[minPrice] = inMin
                it[active] = inActive
            }
            regisResult = getPromoById(getLastId())
        }
        return regisResult
    }
}