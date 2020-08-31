package gilbert.latihan
class DatabaseFood {
    val FOOD_DATABASE: ArrayList<Food> = ArrayList()
    var lastId: Int = 0
    fun showDB(){
        var display: String = "FOOD DATABASE\n===================================================\n"
        for(i in FOOD_DATABASE.indices){
            display = display + "Food "+ FOOD_DATABASE.get(i).foodId                + "\n" +
                    "Name           :" + FOOD_DATABASE.get(i).foodName              + "\n" +
                    "Seller         :" + FOOD_DATABASE.get(i).foodSeller.sellerName + "\n" +
                    "Category       :" + FOOD_DATABASE.get(i).foodCategory          + "\n" +
                    "Price          :" + FOOD_DATABASE.get(i).foodPrice             + "\n" +
                    "+++++++++++++++++++++++++++++++++++++++\n\n"
        }
        println(display)
    }
    fun showFood(food: ArrayList<Food>?){
        var display: String = "FOOD DATABASE\n===================================================\n"
        for(i in food!!.indices){
            display = display + "Food "+ food.get(i).foodId                     + "\n" +
                    "Name           :" + food!!.get(i).foodName                 + "\n" +
                    "Seller         :" + food!!.get(i).foodSeller.sellerName    + "\n" +
                    "Category       :" + food!!.get(i).foodCategory             + "\n" +
                    "Price          :" + food!!.get(i).foodPrice                + "\n" +
                    "+++++++++++++++++++++++++++++++++++++++\n\n"
        }
        println(display)
    }
    fun addFood(food: Food) {
        FOOD_DATABASE.add(food)
        lastId = food.foodId
    }
    fun removeFood(id: Int) : Boolean{
        for(i in FOOD_DATABASE.indices){
            if(FOOD_DATABASE.get(i).foodId == id){
                FOOD_DATABASE.removeAt(i)
                return true
            }
        }
        return false
    }
    fun getFoodById(id: Int) : Food? {
        for(i in FOOD_DATABASE.indices){
            if(FOOD_DATABASE.get(i).foodId == id){
                return FOOD_DATABASE.get(i)
            }
        }
        return null
    }
    fun getFoodBySeller(id: Int) : ArrayList<Food>? {
        var list: ArrayList<Food> = ArrayList()
        for(i in FOOD_DATABASE.indices){
            if(FOOD_DATABASE.get(i).foodSeller.sellerId == id){
                list.add(FOOD_DATABASE.get(i))
            }
        }
        return list
    }
    fun getFoodByCategory(category: FoodCategory) : ArrayList<Food>? {
        var list: ArrayList<Food> = ArrayList()
        for(i in FOOD_DATABASE.indices){
            if(FOOD_DATABASE.get(i).foodCategory == category){
                list.add(FOOD_DATABASE.get(i))
            }
        }
        return list
    }
}