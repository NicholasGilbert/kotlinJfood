package gilbert.latihan
class DatabaseCustomer {
    val CUSTOMER_DATABASE: ArrayList<Customer> = ArrayList()
    var lastId: Int = 0
    fun showDB(){
        var display: String = "CUSTOMER DATABASE\n===================================================\n"
        for(i in CUSTOMER_DATABASE.indices){
            display = display + "Customer " + CUSTOMER_DATABASE.get(i).customerId       + "\n" +
                    "Name           :" + CUSTOMER_DATABASE.get(i).customerName          + "\n" +
                    "Join Date      :" + CUSTOMER_DATABASE.get(i).customerJoindate      + "\n" +
                    "Password       :" + CUSTOMER_DATABASE.get(i).customerPassword      + "\n" +
                    "e-mail         :" + CUSTOMER_DATABASE.get(i).customerEmail         + "\n" +
                    "+++++++++++++++++++++++++++++++++++++++\n\n"
        }
        println(display)
    }
    fun addCustomer(customer: Customer) {
        for (i in CUSTOMER_DATABASE.indices){
            if (CUSTOMER_DATABASE.get(i).customerEmail == customer.customerEmail){
                throw EmailAlreadyExists(customer.customerEmail)
            }
        }
        CUSTOMER_DATABASE.add(customer)
        lastId = customer.customerId
    }
    fun removeCustomer(id: Int) : Boolean{
        for(i in CUSTOMER_DATABASE.indices){
            if(CUSTOMER_DATABASE.get(i).customerId == id){
                CUSTOMER_DATABASE.removeAt(i)
                return true
            }
        }
        return false
    }
    fun getCustomerById(id: Int) : Customer? {
        for(i in CUSTOMER_DATABASE.indices){
            if(CUSTOMER_DATABASE.get(i).customerId == id){
                return CUSTOMER_DATABASE.get(i)
            }
        }
        return null
    }
}