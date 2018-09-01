package au.com.sealink.quickprint.core

import au.com.sealink.printing.ticket_printer.Ticket
import au.com.sealink.printing.ticket_printer.TicketPageSettings

interface Printer {
    fun setTicketPageSettings(settings: TicketPageSettings)
    fun printTickets(tickets: List<Ticket>)
}