package gilbert.latihan

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresCustomer {

    object Customers : Table("customer_database") {
        val id = integer("id")
        val name = varchar("name", 50)
        val email = varchar("email", 50)
        val password = varchar("password", 50)
        val date = date("joindate")
    }

    fun getCustomers() : ArrayList<Customer> {
        var customers: ArrayList<Customer> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for (customer in Customers.selectAll()){
                customers.add(Customer( customer[Customers.id],
                                        customer[Customers.name],
                                        customer[Customers.email],
                                        customer[Customers.password],
                                        customer[Customers.date].toString().take(10)))
            }
        }
        return customers
    }

    fun getCustomerById(id: Int) : Customer?{
        var out: Customer? = null
        transaction {
            PostgresConnect().connect()
            for(customer in Customers.selectAll()){
                if (customer[Customers.id] == id){
                    out = Customer( customer[Customers.id],
                                    customer[Customers.name],
                                    customer[Customers.email],
                                    customer[Customers.password],
                                    customer[Customers.date].toString().take(10))
                }
            }
        }
        return out
    }

    fun login(inEmail: String,
              inPass: String) : Customer?{
        var out: Customer? = null
        transaction {
            PostgresConnect().connect()
            for(customer in Customers.selectAll()){
                if (customer[Customers.email] == inEmail && customer[Customers.password] == inPass){
                    out = Customer( customer[Customers.id],
                                    customer[Customers.name],
                                    customer[Customers.email],
                                    customer[Customers.password],
                                    customer[Customers.date].toString().take(10))
                }
            }
        }
        return out
    }

    fun getLastId() : Int{
        var lastId: Int = 0
        transaction {
            PostgresConnect().connect()
            for(customer in Customers.slice(Customers.id).selectAll().orderBy(Customers.id to SortOrder.DESC).limit(1)) {
                lastId = customer[Customers.id]
            }
        }
        return lastId
    }

    fun addCustomer(inName: String,
                    inEmail: String,
                    inPass: String) : Customer?{
        var regisResult: Customer? = null
        transaction {
         PostgresConnect().connect()
            for(customer in Customers.selectAll()){
                if (customer[Customers.email] == inEmail){
                    throw EmailAlreadyExists(inEmail)
                }
            }
            Customers.insert {
                it[name] = inName
                it[email] = inEmail
                it[password] = inPass
            }
            regisResult = getCustomerById(getLastId())
        }
        return regisResult
    }
}