package au.com.sealink.quickprint

import au.com.sealink.printing.ticket_printer.*
import au.com.sealink.printing.ticket_printer.Ticket
import au.com.sealink.quickprint.core.PrinterRepository
import au.com.sealink.quickprint.requests.*
import org.springframework.web.bind.annotation.*
import kotlinx.coroutines.*

import java.util.*

@RestController
class ApplicationController(private val repository: PrinterRepository) {
    private val rb = ResourceBundle.getBundle("version")

    @GetMapping("/")
    fun index() = rb.getString("version")

    @GetMapping("/printers")
    fun printers()= repository.findAll().map { it.name }

    @PostMapping("/print-receipts")
    fun printReceipts(@RequestBody request: PrintReceipt) : Response {
        val unsupportedTypes = EnumSet.of(ElementType.Barcode, ElementType.Image)
        val printer = ReceiptPrinter(request.printerName)
        val tickets = request.tickets.map {
            val ticket = Ticket()
            ticket.addElements(
                    it.asSequence()
                        .filter { el -> !unsupportedTypes.contains(el.type) }
                        .map { el -> el.toTicketElement() }
                        .toList()
            )
            ticket
        }

        GlobalScope.launch {
            printer.printTickets(tickets)
        }

        return Response()
    }

    @PostMapping("/print-tickets")
    fun printTickets(@RequestBody request: PrintTicket) : Response {
        val printer = repository.requestPrinter(request.printerName)
        val settings = TicketPageSettings(request.pageFormat.width,
                request.pageFormat.height,
                request.pageFormat.margin_x ?: 0.0,
                request.pageFormat.margin_y ?: 0.0)

        printer.setTicketPageSettings(settings)

        val tickets = request.tickets.map {
            val ticket = Ticket()
            ticket.addElements(it.map { el -> el.toTicketElement() })
            ticket
        }

        GlobalScope.launch {
            printer.printTickets(tickets)
        }
        return Response()
    }
}