package gilbert.latihan.controller
import gilbert.latihan.*
import org.springframework.web.bind.annotation.*
@RequestMapping("/seller")
@RestController

class SellerController {
    @RequestMapping("/getsellers",
                    method = arrayOf(RequestMethod.GET))
    fun getCustomers() : ArrayList<Seller>?{
        return PostgresSeller().getSellers()
    }

    @RequestMapping("/{id}",
                    method = arrayOf(RequestMethod.GET))
    fun getSellerById(@PathVariable id: Int) : Seller?{
        return PostgresSeller().getSellerById(id)
    }

    @RequestMapping("/register",
                    method = arrayOf(RequestMethod.POST))
    fun addSeller ( @RequestParam  ( "name") inName: String,
                    @RequestParam  ( "email") inEmail: String,
                    @RequestParam  ( "phone") inPhone: String,
                    @RequestParam  ( "location") inlocation: Int) : Seller?{
        return PostgresSeller().addSeller(inName, inEmail, inPhone, inlocation)
    }
}