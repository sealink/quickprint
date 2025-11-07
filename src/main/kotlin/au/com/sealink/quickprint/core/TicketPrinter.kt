package au.com.sealink.quickprint.core

import au.com.sealink.printing.ticketprinter.Ticket
import au.com.sealink.printing.ticketprinter.TicketPageSettings
import au.com.sealink.printing.ticketprinter.TicketPrinter
import java.time.LocalDateTime

class TicketPrinter(private var name: String) : Printer {
    private val mPrinter : TicketPrinter = TicketPrinter()

    init {
        System.err.println("[${LocalDateTime.now()}] TicketPrinter INIT: printer='$name'")
        mPrinter.setPrinter(this.name)
        System.err.println("[${LocalDateTime.now()}] TicketPrinter INIT SUCCESS: printer='$name'")
    }

    override fun setTicketPageSettings(settings: TicketPageSettings) {
        System.err.println("[${LocalDateTime.now()}] TicketPrinter SET_SETTINGS: printer='$name'")
        mPrinter.setTicketPageSettings(settings)
    }

    override fun printTickets(tickets: List<Ticket>) {
        try {
            System.err.println("[${LocalDateTime.now()}] TicketPrinter PRINT START: printer='$name', tickets=${tickets.size}, thread=${Thread.currentThread().name}")
            mPrinter.printTickets(tickets)
            System.err.println("[${LocalDateTime.now()}] TicketPrinter PRINT SUCCESS: printer='$name'")
        } catch (e: Exception) {
            System.err.println("[${LocalDateTime.now()}] TicketPrinter PRINT FAILED: printer='$name', error=${e.javaClass.name}: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}