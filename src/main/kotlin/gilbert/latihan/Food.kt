package gilbert.latihan
class Food (id: Int, name: String, price: Int, category: FoodCategory, seller: Seller) {
    var foodId = id
    var foodName = name
    var foodPrice= price
    var foodCategory = category
    var foodSeller = seller

    init{
        val display: String = "Food ${foodName} \n " +
                "ID         : " + foodId                +" \n " +
                "Price      : " + foodPrice             +" \n " +
                "Category   : " + foodCategory          +" \n " +
                "Sold by    : " + foodSeller.sellerName +" \n "
        println(display)
    }
    fun show(){
        val display: String = "Food ${foodName} \n " +
                "ID         : " + foodId                                                +" \n " +
                "Price      : " + foodPrice                                             +" \n " +
                "Category   : " + foodCategory.toString().toLowerCase().capitalize()    +" \n " +
                "Sold by    : " + foodSeller.sellerName                                 +" \n "
        println(display)
    }
}