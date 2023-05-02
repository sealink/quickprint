package au.com.sealink.quickprint.core

import au.com.sealink.printing.ticketprinter.Ticket
import au.com.sealink.printing.ticketprinter.TicketPageSettings

interface Printer {
    fun setTicketPageSettings(settings: TicketPageSettings)
    fun printTickets(tickets: List<Ticket>)
}