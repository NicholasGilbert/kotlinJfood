package gilbert.latihan

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.transactions.transaction
class PostgresFood {

    object Foods : Table("food_database") {
        val id = integer("id")
        val name = varchar("name", 50)
        val seller = integer("seller_id")
        val price = integer("price")
//        val category = enumerationByName("category", 50, FoodCategory::class)
        val category = varchar("category", 50)
    }

    fun getFoods() : ArrayList<Food> {
        var foods: ArrayList<Food> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for (food in PostgresFood.Foods.selectAll()){
                foods.add(Food( food[PostgresFood.Foods.id],
                                food[PostgresFood.Foods.name],
                                food[PostgresFood.Foods.price],
                                FoodCategory.valueOf(food[PostgresFood.Foods.category]),
                                PostgresSeller().getSellerById(food[PostgresFood.Foods.seller])!!))
            }
        }
        return foods
    }

    fun getFoodById(id: Int) : Food?{
        var out: Food? = null
        transaction {
            PostgresConnect().connect()
            for(food in Foods.selectAll()){
                if (food[Foods.id] == id){
                    out = Food( food[PostgresFood.Foods.id],
                                food[PostgresFood.Foods.name],
                                food[PostgresFood.Foods.price],
                                FoodCategory.valueOf(food[PostgresFood.Foods.category]),
                                PostgresSeller().getSellerById(food[PostgresFood.Foods.seller])!!)
                }
            }
        }
        return out
    }
    fun getFoodBySeller(id: Int) : ArrayList<Food>{
        var out: ArrayList<Food> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for(food in Foods.selectAll()){
                if (food[Foods.seller] == id){
                    out.add(Food(   food[PostgresFood.Foods.id],
                                    food[PostgresFood.Foods.name],
                                    food[PostgresFood.Foods.price],
                                    FoodCategory.valueOf(food[PostgresFood.Foods.category]),
                                    PostgresSeller().getSellerById(food[PostgresFood.Foods.seller])!!))
                }
            }
        }
        return out
    }

    fun getFoodByCategory(category: String) : ArrayList<Food>{
        var out: ArrayList<Food> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for(food in Foods.selectAll()){
                if (food[Foods.category].toString().equals(category)){
                    out.add(Food(   food[PostgresFood.Foods.id],
                                    food[PostgresFood.Foods.name],
                                    food[PostgresFood.Foods.price],
                                    FoodCategory.valueOf(food[PostgresFood.Foods.category]),
                                    PostgresSeller().getSellerById(food[PostgresFood.Foods.seller])!!))
                }
            }
        }
        return out
    }

    fun getLastId() : Int{
        var lastId: Int = 0
        transaction {
            PostgresConnect().connect()
            for(food in Foods.slice(Foods.id).selectAll().orderBy(Foods.id to SortOrder.DESC).limit(1)) {
                lastId = food[Foods.id]
            }
        }
        return lastId
    }

    fun addFood(inName: String, inSellerId: Int, inPrice: Int, inCategory: FoodCategory) : Food?{
        var addResult: Food? = null
        transaction {
            PostgresConnect().connect()
            for(food in Foods.selectAll()){
                if (food[Foods.name] == inName && food[Foods.seller] == inSellerId){
                    throw FoodBySellerExists(inName, PostgresSeller().getSellerById(inSellerId)!!.sellerName)
                }
            }
            Foods.insert {
                it[name] = inName
                it[seller] = inSellerId
                it[price] = inPrice
                it[category] = inCategory.toString()
            }
            addResult = getFoodById(getLastId())
        }
        return addResult
    }
}