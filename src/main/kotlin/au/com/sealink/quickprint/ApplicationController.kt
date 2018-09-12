package au.com.sealink.quickprint

import au.com.sealink.printing.ticket_printer.*
import au.com.sealink.quickprint.core.PrinterRepository
import au.com.sealink.quickprint.requests.PrintTicket
import au.com.sealink.quickprint.requests.toTicketElement
import kotlinx.coroutines.experimental.async
import org.springframework.web.bind.annotation.*
import java.util.ResourceBundle

@RestController
class ApplicationController(private val repository: PrinterRepository) {
    private val rb = ResourceBundle.getBundle("version")

    @GetMapping("/")
    fun index() = rb.getString("version")

    @GetMapping("/printers")
    fun printers()= repository.findAll().map { it.name }

    @PostMapping("/print-tickets")
    fun printTickets(@RequestBody request: PrintTicket) {
        val printer = repository.requestPrinter(request.printerName)
        val settings = TicketPageSettings(request.pageFormat.width,
                request.pageFormat.height,
                request.pageFormat.margin ?: 0.0)

        printer.setTicketPageSettings(settings)

        val tickets = request.tickets.map { it ->
            val ticket = Ticket()
            it.map { el -> el.toTicketElement() }.forEach { ticket.addElement(it) }
            ticket
        }

        tickets.forEach {
            async {
                printer.printTickets(tickets)
            }
        }
    }
}