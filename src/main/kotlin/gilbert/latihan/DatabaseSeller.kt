package gilbert.latihan
class DatabaseSeller (){
    val SELLER_DATABASE: ArrayList<Seller> = ArrayList()
    var lastId: Int = 0

    fun showDB(){
        var display: String = "SELLER DATABASE\n===================================================\n"
        for(i in SELLER_DATABASE.indices){
            display = display + "Seller "          + SELLER_DATABASE.get(i).sellerId                    + "\n" +
                                "Name           :" + SELLER_DATABASE.get(i).sellerName                  + "\n" +
                                "e-mail         :" + SELLER_DATABASE.get(i).sellerEmail                 + "\n" +
                                "Phone Number   :" + SELLER_DATABASE.get(i).sellerPhoneNumber           + "\n" +
                                "Location       :" + SELLER_DATABASE.get(i).sellerLocation.locationCity + "\n" +
                                "+++++++++++++++++++++++++++++++++++++++\n\n"
        }
        println(display)
    }
    fun addSeller(seller: Seller) {
        SELLER_DATABASE.add(seller)
        lastId = seller.sellerId
        println(lastId)
    }
    fun removeSeller(id: Int) : Boolean{
        for(i in SELLER_DATABASE.indices){
            if(SELLER_DATABASE.get(i).sellerId == id){
                SELLER_DATABASE.removeAt(i)
                return true
            }
        }
        return false
    }
    fun getSellerById(id: Int) : Seller? {
        for(i in SELLER_DATABASE.indices){
            if(SELLER_DATABASE.get(i).sellerId == id){
                return SELLER_DATABASE.get(i)
            }
        }
        return null
    }
}