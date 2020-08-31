package gilbert.latihan
class PromoCodeAlreadyExists (inp: String) : Exception() {
    val promo: String = inp
    fun message() : String{
        return "Promo code: " + promo + " already exists"
    }
}