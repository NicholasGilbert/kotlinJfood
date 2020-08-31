package gilbert.latihan
class DatabasePromo {
        val PROMO_DATABASE: ArrayList<Promo> = ArrayList()
    var lastId: Int = 0
    fun showDB(){
        var display: String = "PROMO DATABASE\n===================================================\n"
        for(i in PROMO_DATABASE.indices){
            display = display + "Promo "          + PROMO_DATABASE.get(i).promoId   + "\n" +
                    "Promo Code     :" + PROMO_DATABASE.get(i).promoCode            + "\n" +
                    "Promo Status   :" + PROMO_DATABASE.get(i).promoActive          + "\n" +
                    "Minimum Payment:" + PROMO_DATABASE.get(i).promoMinPrice        + "\n" +
                    "Discount       :" + PROMO_DATABASE.get(i).promoDiscount        + "\n" +
                    "+++++++++++++++++++++++++++++++++++++++\n\n"
        }
        println(display)
    }
    fun addPromo(promo: Promo) {
        for (i in PROMO_DATABASE.indices){
            if (PROMO_DATABASE.get(i).promoCode == promo.promoCode){
                throw PromoCodeAlreadyExists(promo.promoCode)
            }
        }
        PROMO_DATABASE.add(promo)
        lastId = promo.promoId
    }
    fun removePromo(id: Int) : Boolean{
        for(i in PROMO_DATABASE.indices){
            if(PROMO_DATABASE.get(i).promoId == id){
                PROMO_DATABASE.removeAt(i)
                return true
            }
        }
        return false
    }
    fun getPromoById(id: Int) : Promo? {
        for(i in PROMO_DATABASE.indices){
            if(PROMO_DATABASE.get(i).promoId == id){
                return PROMO_DATABASE.get(i)
            }
        }
        return null
    }
    fun activatePromo(id: Int) : Boolean {
        for(i in PROMO_DATABASE.indices){
            if(PROMO_DATABASE.get(i).promoId == id){
                PROMO_DATABASE.get(i).promoActive = true
                return true
            }
        }
        return false
    }
    fun deactivatePromo(id: Int) : Boolean {
        for(i in PROMO_DATABASE.indices){
            if(PROMO_DATABASE.get(i).promoId == id){
                PROMO_DATABASE.get(i).promoActive = false
                return true
            }
        }
        return false
    }
}