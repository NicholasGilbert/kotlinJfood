package gilbert.latihan.controller
import gilbert.latihan.*
import org.apache.el.stream.Optional
import org.springframework.web.bind.annotation.*
@RequestMapping("/invoice")
@RestController

class InvoiceController {
    @RequestMapping("/{id}",
            method = arrayOf(RequestMethod.GET))
    fun getInvoiceById(@PathVariable id: Int) : Invoice?{
        return PostgresInvoice().getInvoiceById(id)
    }

    @RequestMapping("/customer/{id}",
            method = arrayOf(RequestMethod.GET))
    fun getInvoiceByCustomerId(@PathVariable id: Int) : ArrayList<Invoice>?{
        return PostgresInvoice().getInvoiceByCustomerId(id)
    }

    @RequestMapping("/{id}",
            method = arrayOf(RequestMethod.PUT))
    fun changeStatus(@PathVariable id: Int,
                     @RequestParam  ( "status") inStatus: String) : Invoice?{
        return PostgresInvoice().changeStatus(id, inStatus)
    }

    @RequestMapping("/getinvoices",
            method = arrayOf(RequestMethod.GET))
    fun getInvoices() : ArrayList<Invoice>?{
        return PostgresInvoice().getInvoices()
    }

    @RequestMapping("/cashorder",
            method = arrayOf(RequestMethod.POST))
    fun addCashInvoice (    @RequestParam  ( "food") inName: Int,
                        @RequestParam  ( "customer") inCustomer: Int,
                        @RequestParam  ( "delivery", required = false) inDelivery: Int?) : Invoice?{
        var deliv: Int? = inDelivery
        if(inDelivery == null){
            deliv = 0
        }
        return PostgresInvoice().addCashInvoice(inName, inCustomer, deliv!!)
    }

    @RequestMapping("/cashlessorder",
            method = arrayOf(RequestMethod.POST))
    fun addCashlessInvoice (    @RequestParam  ( "food") inName: Int,
                        @RequestParam  ( "customer") inCustomer: Int,
                        @RequestParam  ( "promo", required = false) inPromo: String?) : Invoice?{
        var promo: String? = inPromo
        if(inPromo == null){
            promo = ""
        }
        return PostgresInvoice().addCashlessInvoice(inName, inCustomer, promo!!)
    }
}