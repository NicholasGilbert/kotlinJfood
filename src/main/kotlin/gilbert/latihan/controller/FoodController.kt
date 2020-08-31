package gilbert.latihan.controller
import gilbert.latihan.*
import org.springframework.web.bind.annotation.*
@RequestMapping("/food")
@RestController
class FoodController {

    @RequestMapping("/getfoods",
                    method = arrayOf(RequestMethod.GET))
    fun getFoods() : ArrayList<Food>?{
        return PostgresFood().getFoods()
    }

    @RequestMapping("/{id}",
                    method = arrayOf(RequestMethod.GET))
    fun getFoodById(@PathVariable id: Int) : Food?{
        return PostgresFood().getFoodById(id)
    }

    @RequestMapping("/seller/{id}",
                    method = arrayOf(RequestMethod.GET))
    fun getFoodBySeller(@PathVariable id: Int) : ArrayList<Food>{
        return PostgresFood().getFoodBySeller(id)
    }

    @RequestMapping("/category",
                    method = arrayOf(RequestMethod.GET))
    fun getFoodByCategory(@RequestParam  ( "category") inCategory: String) : ArrayList<Food>{
        return PostgresFood().getFoodByCategory(inCategory)
    }

    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    fun addFood(@RequestParam  ( "name") inName: String,
                @RequestParam  ( "seller") inSeller: Int,
                @RequestParam  ( "price") inPrice: Int,
                @RequestParam  ( "category") inCategory: String) : Food?{
        return PostgresFood().addFood(inName, inSeller, inPrice, FoodCategory.valueOf(inCategory))
    }
}