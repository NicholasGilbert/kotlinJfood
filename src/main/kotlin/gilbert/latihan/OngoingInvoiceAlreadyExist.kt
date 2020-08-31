package gilbert.latihan
class OngoingInvoiceAlreadyExist (inp: String) : Exception() {
    val invoice: String = inp
    fun message() : String{
        return invoice + "has ongoing invoice"
    }
}