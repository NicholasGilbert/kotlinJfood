package gilbert.latihan.controller
import gilbert.latihan.*
import org.springframework.web.bind.annotation.*
@RequestMapping("/promo")
@RestController

class PromoController {
    @RequestMapping("/{code}",
            method = arrayOf(RequestMethod.GET))
    fun getPromoByCode(@PathVariable code: String) : Promo?{
        return PostgresPromo().getPromoByCode(code)
    }

    @RequestMapping("/getpromos",
            method = arrayOf(RequestMethod.GET))
    fun getPromos() : ArrayList<Promo>?{
        return PostgresPromo().getPromos()
    }

    @RequestMapping("/register",
            method = arrayOf(RequestMethod.POST))
    fun addPromo (  @RequestParam  ( "code") inCode: String,
                    @RequestParam  ( "discount") inDis: Int,
                    @RequestParam  ( "minprice") inMin: Int,
                    @RequestParam  ( "active") inActive: Boolean) : Promo?{
        return PostgresPromo().addPromo(inCode, inDis, inMin, inActive)
    }
}