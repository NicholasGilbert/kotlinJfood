package gilbert.latihan.controller
import gilbert.latihan.*
import org.springframework.web.bind.annotation.*
@RequestMapping("/customer")
@RestController

class CustomerController {
    @RequestMapping("/{id}",
                    method = arrayOf(RequestMethod.GET))
    fun getCustomerById(@PathVariable id: Int) : Customer?{
        return PostgresCustomer().getCustomerById(id)
    }

    @RequestMapping("/getcustomers",
                    method = arrayOf(RequestMethod.GET))
    fun getCustomers() : ArrayList<Customer>?{
        return PostgresCustomer().getCustomers()
    }

    @RequestMapping("/login",
                    method = arrayOf(RequestMethod.POST))
    fun login (      @RequestParam  ( "email") inEmail: String,
                     @RequestParam  ( "password") inPass: String) : Customer?{
        return PostgresCustomer().login(inEmail, inPass)
    }

    @RequestMapping("/register",
                    method = arrayOf(RequestMethod.POST))
    fun addCustomer (@RequestParam  ( "name") inName: String,
                     @RequestParam  ( "email") inEmail: String,
                     @RequestParam  ( "password") inPass: String) : Customer?{
        return PostgresCustomer().addCustomer(inName, inEmail, inPass)
    }
}