package gilbert.latihan

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Jfood {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            var sellerdb = DatabaseSeller()
//            var fooddb = DatabaseFood()
//            var customerdb = DatabaseCustomer()
//            var promodb = DatabasePromo()
//            var invoicedb = DatabaseInvoice()
//            val location = Location("Skyrim", "Cold northern region in the north of Tamriel, home of the nords", "Whiterun")
//            sellerdb.addSeller(Seller(sellerdb.lastId + 1, "Brynjolf", "bryn@thieves.guild.com", "235252352", location))
//            sellerdb.addSeller(Seller(sellerdb.lastId + 1, "Maiq the Liar", "maiq@traveller.com", "13235235", location))
//            customerdb.addCustomer(Customer(customerdb.lastId + 1, "Maven Black Briar", "maven@briar.com", "i love nazeem"))
//            customerdb.addCustomer(Customer(customerdb.lastId + 1, "Legate Rikke", "rikke@imperial.com", "i love tulius"))
//            fooddb.addFood(Food(fooddb.lastId + 1, "Falmer Blood Elixir", 15000, FoodCategory.Elixir, sellerdb.getSellerById(1)!!))
//            fooddb.addFood(Food(fooddb.lastId + 1, "Fake Falmer Blood Elixir", 1000, FoodCategory.Elixir, sellerdb.getSellerById(2)!!))
//            try {
//                promodb.addPromo(Promo(promodb.lastId + 1, "abcd", 7000, 5000, true))
//                promodb.addPromo(Promo(promodb.lastId + 1, "abcd", 10000, 5000, false))
//            } catch (e: PromoCodeAlreadyExists) {
//                println(e.message())
//            }
//            try {
//                invoicedb.addInvoice(CashInvoice(invoicedb.lastId + 1, fooddb.getFoodById(1)!!, customerdb.getCustomerById(1)!!))
//                invoicedb.addInvoice(CashInvoice(invoicedb.lastId + 1, fooddb.getFoodById(2)!!, customerdb.getCustomerById(1)!!))
//            } catch (e: OngoingInvoiceAlreadyExist) {
//                println(e.message())
//            }
//            try {
//                invoicedb.addInvoice(CashlessInvoice(invoicedb.lastId + 1, fooddb.getFoodById(1)!!, customerdb.getCustomerById(2)!!))
//                invoicedb.addInvoice(CashlessInvoice(invoicedb.lastId + 1, fooddb.getFoodById(1)!!, customerdb.getCustomerById(2)!!))
//            } catch (e: OngoingInvoiceAlreadyExist) {
//                println(e.message())
//            }
//            sellerdb.showDB()
//            customerdb.showDB()
//            fooddb.showDB()
//            promodb.activatePromo(2)
//            promodb.showDB()
//            invoicedb.changeStatus(1, InvoiceStatus.Finished)
//            invoicedb.showDB()

//            var postgresCustomer = PostgresCustomer()
//            postgresCustomer.connect()
//            postgresCustomer.showdb()
            PostgresConnect().connect()
            SpringApplication.run(Jfood::class.java, *args)
        }
    }
}