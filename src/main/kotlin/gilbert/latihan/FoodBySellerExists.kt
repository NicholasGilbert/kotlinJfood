package gilbert.latihan

class FoodBySellerExists (food: String, seller: String) : Exception() {
    val foodName: String = food
    val sellerName: String = seller

    fun message() : String{
        return foodName + "is already sold by" + sellerName
    }
}