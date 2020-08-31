package gilbert.latihan
class DatabaseInvoice {
    val INVOICE_DATABASE: ArrayList<Invoice> = ArrayList()
    var lastId: Int = 0
    fun showDB(){
        var display: String = "Invoice DATABASE\n===================================================\n"
        for(i in INVOICE_DATABASE.indices){
            display = display + "Invoice "+ INVOICE_DATABASE.get(i).invoiceId               + "\n" +
                    "Date       :" + INVOICE_DATABASE.get(i).invoiceDate                    + "\n" +
                    "Food       :" + INVOICE_DATABASE.get(i).invoicefood.foodName           + "\n" +
                    "Status     :" + INVOICE_DATABASE.get(i).invoiceStatus                  + "\n" +
                    "Payment    :" + INVOICE_DATABASE.get(i).invoicePaymentType             + "\n" +
                    "Customer   :" + INVOICE_DATABASE.get(i).invoiceCustomer.customerName   + "\n" +
                    "Total      :" + INVOICE_DATABASE.get(i).invoiceTotalPrice              + "\n" +
                    "+++++++++++++++++++++++++++++++++++++++\n\n"
        }
        println(display)
    }
    fun addInvoice(invoice: Invoice) {
        for (i in INVOICE_DATABASE.indices){
            if (INVOICE_DATABASE.get(i).invoiceCustomer == invoice.invoiceCustomer && INVOICE_DATABASE.get(i).invoiceStatus == InvoiceStatus.Ongoing){
                throw OngoingInvoiceAlreadyExist(invoice.invoiceCustomer.customerName)
            }
        }
        INVOICE_DATABASE.add(invoice)
        lastId = invoice.invoiceId
    }
    fun removeInvoice(id: Int) : Boolean{
        for(i in INVOICE_DATABASE.indices){
            if(INVOICE_DATABASE.get(i).invoiceId == id){
                INVOICE_DATABASE.removeAt(i)
                return true
            }
        }
        return false
    }
    fun getInvoiceById(id: Int) : Invoice? {
        for(i in INVOICE_DATABASE.indices){
            if(INVOICE_DATABASE.get(i).invoiceId == id){
                return INVOICE_DATABASE.get(i)
            }
        }
        return null
    }
    fun changeStatus(id: Int, status: InvoiceStatus) : Boolean {
        for(i in INVOICE_DATABASE.indices){
            if(INVOICE_DATABASE.get(i).invoiceId == id){
                INVOICE_DATABASE.get(i).invoiceStatus = status
                return true
            }
        }
        return false
    }
    fun getInvoiceByCustomer(id: Int) : ArrayList<Invoice>? {
        var list: ArrayList<Invoice> = ArrayList()
        for(i in INVOICE_DATABASE.indices){
            if(INVOICE_DATABASE.get(i).invoiceCustomer.customerId == id){
                list.add(INVOICE_DATABASE.get(i))
            }
        }
        return list
    }
}