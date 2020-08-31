package gilbert.latihan

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresInvoice {
    object Invoices : Table("invoice_database") {
        val id = Invoices.integer("id")
        val food = Invoices.integer("food_id")
        val date = date("date")
        val customer = Invoices.integer("customer_id")
        val status = Invoices.varchar("status", 20)
        val payment = Invoices.varchar("payment", 20)
        val delivery = Invoices.integer("delivery_fee").nullable()
        val promo = Invoices.integer("promo_id").nullable()
        val price = Invoices.integer("total_price")
    }

    fun getLastId() : Int{
        var lastId: Int = 0
        transaction {
            PostgresConnect().connect()
            for(invoice in Invoices.slice(Invoices.id).selectAll().orderBy(Invoices.id to SortOrder.DESC).limit(1)) {
                lastId = invoice[Invoices.id]
            }
        }
        return lastId
    }

    fun getInvoiceById(id: Int) : Invoice? {
        var out: Invoice? = null
        transaction {
            PostgresConnect().connect()
            for (invoice in Invoices.selectAll()){
                if(invoice[Invoices.id] == id) {
                    if (invoice[Invoices.payment].equals(PaymentType.Cash.toString())) {
                        if (invoice[Invoices.delivery] == 0 || invoice[Invoices.delivery] == null) {
                            out = (CashInvoice(invoice[Invoices.id],
                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                    invoice[Invoices.date].toString(),
                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                    InvoiceStatus.valueOf(invoice[Invoices.status])))
                        } else {
                            out = (CashInvoice(invoice[Invoices.id],
                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                    invoice[Invoices.date].toString(),
                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                    InvoiceStatus.valueOf(invoice[Invoices.status]),
                                    invoice[Invoices.delivery]!!))
                        }
                    } else {
                        if (invoice[Invoices.promo] == null) {
                            out = (CashlessInvoice( invoice[Invoices.id],
                                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                                    invoice[Invoices.date].toString(),
                                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                                    InvoiceStatus.valueOf(invoice[Invoices.status])))
                        } else {
                            out = (CashlessInvoice(invoice[Invoices.id],
                                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                                    invoice[Invoices.date].toString(),
                                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                                    InvoiceStatus.valueOf(invoice[Invoices.status]),
                                                    PostgresPromo().getPromoById(invoice[Invoices.promo]!!)!!))
                        }
                    }
                }
            }
        }
        return out
    }

    fun getInvoiceByCustomerId(id: Int) : ArrayList<Invoice>? {
        var out: ArrayList<Invoice> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for (invoice in Invoices.selectAll()){
                if(invoice[Invoices.customer] == id) {
                    if (invoice[Invoices.payment].equals(PaymentType.Cash.toString())) {
                        if (invoice[Invoices.delivery] == 0 || invoice[Invoices.delivery] == null) {
                            out.add((CashInvoice(invoice[Invoices.id],
                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                    invoice[Invoices.date].toString(),
                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                    InvoiceStatus.valueOf(invoice[Invoices.status]))))
                        } else {
                            out.add((CashInvoice(invoice[Invoices.id],
                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                    invoice[Invoices.date].toString(),
                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                    InvoiceStatus.valueOf(invoice[Invoices.status]),
                                    invoice[Invoices.delivery]!!)))
                        }
                    } else {
                        if (invoice[Invoices.promo] == null) {
                            out.add((CashlessInvoice( invoice[Invoices.id],
                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                    invoice[Invoices.date].toString(),
                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                    InvoiceStatus.valueOf(invoice[Invoices.status]))))
                        } else {
                            out.add((CashlessInvoice(invoice[Invoices.id],
                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                    invoice[Invoices.date].toString(),
                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                    InvoiceStatus.valueOf(invoice[Invoices.status]),
                                    PostgresPromo().getPromoById(invoice[Invoices.promo]!!)!!)))
                        }
                    }
                }
            }
        }
        return out
    }

    fun getInvoices() : ArrayList<Invoice> {
        var invoices: ArrayList<Invoice> = ArrayList()
        transaction {
            PostgresConnect().connect()
            for (invoice in Invoices.selectAll()){
                if (invoice[Invoices.payment].equals(PaymentType.Cash.toString())){
                    if (invoice[Invoices.delivery] == 0 || invoice[Invoices.delivery] == null) {
                        invoices.add(CashInvoice(   invoice[Invoices.id],
                                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                                    invoice[Invoices.date].toString(),
                                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                                    InvoiceStatus.valueOf(invoice[Invoices.status])))
                    }
                    else{
                        invoices.add(CashInvoice(   invoice[Invoices.id],
                                                    PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                                    invoice[Invoices.date].toString(),
                                                    PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                                    InvoiceStatus.valueOf(invoice[Invoices.status]),
                                                    invoice[Invoices.delivery]!!))
                    }
                }
                else{
                    if(invoice[Invoices.promo] == null){
                        invoices.add(CashlessInvoice(   invoice[Invoices.id],
                                                        PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                                        invoice[Invoices.date].toString(),
                                                        PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                                        InvoiceStatus.valueOf(invoice[Invoices.status])))
                    }
                    else{
                        invoices.add(CashlessInvoice(   invoice[Invoices.id],
                                                        PostgresFood().getFoodById(invoice[Invoices.food])!!,
                                                        invoice[Invoices.date].toString(),
                                                        PostgresCustomer().getCustomerById(invoice[Invoices.customer])!!,
                                                        InvoiceStatus.valueOf(invoice[Invoices.status]),
                                                        PostgresPromo().getPromoById(invoice[Invoices.promo]!!)!!))
                    }
                }
            }
        }
        return invoices
    }

    fun addCashInvoice( inFood: Int,
                        inCustomer: Int,
                        inDelivery: Int) : Invoice?{
        val cashOrder: CashInvoice = CashInvoice(1,
                                                    PostgresFood().getFoodById(inFood)!!,
                                                    "",
                                                    PostgresCustomer().getCustomerById(inCustomer)!!,
                                                    InvoiceStatus.Ongoing,
                                                    inDelivery)
        var regisResult: Invoice? = null
        transaction {
            PostgresConnect().connect()
            for(invoice in Invoices.selectAll()){
                if (invoice[Invoices.customer] == inCustomer && invoice[Invoices.status].equals(InvoiceStatus.Ongoing.toString())){
                    throw OngoingInvoiceAlreadyExist(PostgresCustomer().getCustomerById(inCustomer)!!.customerName)
                }
            }
            if(inDelivery == 0){
                Invoices.insert {
                    it[food] = inFood
                    it[customer] = inCustomer
                    it[status] = InvoiceStatus.Ongoing.toString()
                    it[payment] = PaymentType.Cash.toString()
                    it[price] = cashOrder.invoiceTotalPrice
                }
            }
            else {
                Invoices.insert {
                    it[food] = inFood
                    it[customer] = inCustomer
                    it[status] = InvoiceStatus.Ongoing.toString()
                    it[payment] = PaymentType.Cash.toString()
                    it[delivery] = inDelivery
                    it[price] = cashOrder.invoiceTotalPrice
                }
            }
            regisResult = getInvoiceById(getLastId())
        }
        return regisResult
    }

    fun addCashlessInvoice( inFood: Int,
                            inCustomer: Int,
                            inPromo: String) : Invoice?{
        val cashOrder: CashlessInvoice = CashlessInvoice(1,
                                                            PostgresFood().getFoodById(inFood)!!,
                                                        "",
                                                            PostgresCustomer().getCustomerById(inCustomer)!!,
                                                            InvoiceStatus.Ongoing,
                                                            PostgresPromo().getPromoByCode(inPromo)!!)
        var regisResult: Invoice? = null
        transaction {
            PostgresConnect().connect()
            for(invoice in Invoices.selectAll()){
                if (invoice[Invoices.customer] == inCustomer && invoice[Invoices.status].equals(InvoiceStatus.Ongoing.toString())){
                    throw OngoingInvoiceAlreadyExist(PostgresCustomer().getCustomerById(inCustomer)!!.customerName)
                }
            }
            if(inPromo == ""){
                Invoices.insert {
                    it[food] = inFood
                    it[customer] = inCustomer
                    it[status] = InvoiceStatus.Ongoing.toString()
                    it[payment] = PaymentType.Cashless.toString()
                    it[price] = cashOrder.invoiceTotalPrice
                }
            }
            else {
                Invoices.insert {
                    it[food] = inFood
                    it[customer] = inCustomer
                    it[status] = InvoiceStatus.Ongoing.toString()
                    it[payment] = PaymentType.Cashless.toString()
                    it[promo] = PostgresPromo().getPromoByCode(inPromo)!!.promoId
                    it[price] = cashOrder.invoiceTotalPrice
                }
            }
            regisResult = getInvoiceById(getLastId())
        }
        return regisResult
    }

    fun changeStatus (  inID: Int,
                        inStatus: String) : Invoice?{
        transaction {
            Invoices.update ({Invoices.id eq inID}){
                it[status] = InvoiceStatus.valueOf(inStatus).toString()
            }
        }
        return getInvoiceById(inID)
    }
}