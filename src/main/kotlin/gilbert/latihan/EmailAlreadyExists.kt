package gilbert.latihan
class EmailAlreadyExists (inp: String) : Exception(){
    val customer: String = inp
    fun message() : String{
        return "The e-mail:'" + customer + "' is already registered"
    }
}