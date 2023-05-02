package au.com.sealink.quickprint.core

import au.com.sealink.printing.ticketprinter.Ticket
import au.com.sealink.printing.ticketprinter.TicketPageSettings
import au.com.sealink.printing.ticketprinter.TicketPrinter

class TicketPrinter(private var name: String) : Printer {
    private val mPrinter : TicketPrinter = TicketPrinter()

    init {
        mPrinter.setPrinter(this.name)
    }

    override fun setTicketPageSettings(settings: TicketPageSettings) {
        mPrinter.setTicketPageSettings(settings)
    }

    override fun printTickets(tickets: List<Ticket>) {
        mPrinter.printTickets(tickets)
    }
}