package au.com.sealink.quickprint

import au.com.sealink.printing.ticketprinter.*
import au.com.sealink.printing.ticketprinter.Ticket
import au.com.sealink.quickprint.core.PrinterRepository
import au.com.sealink.quickprint.requests.*
import org.springframework.web.bind.annotation.*
import org.springframework.http.MediaType
import kotlinx.coroutines.*
import java.time.LocalDateTime

import java.util.*

@RestController
class ApplicationController(private val repository: PrinterRepository) {
    private val rb = ResourceBundle.getBundle("version")

    @GetMapping("/", produces = [MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"])
    fun index() = rb.getString("version")

    @GetMapping("/printers")
    fun printers()= repository.findAll().map { it.name }

    @PostMapping("/print-receipts")
    fun printReceipts(@RequestBody request: PrintReceipt) : Response {
        System.err.println("[${LocalDateTime.now()}] RECEIPTS: printer='${request.printerName}', tickets=${request.tickets.size}")
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
            try {
                printer.printTickets(tickets)
                System.err.println("[${LocalDateTime.now()}] RECEIPTS SUCCESS: printer='${request.printerName}'")
            } catch (e: Exception) {
                System.err.println("[${LocalDateTime.now()}] RECEIPTS FAILED: printer='${request.printerName}', error=${e.javaClass.name}: ${e.message}")
                e.printStackTrace()
            }
        }

        return Response()
    }

    @PostMapping("/print-tickets")
    fun printTickets(@RequestBody request: PrintTicket) : Response {
        System.err.println("[${LocalDateTime.now()}] TICKETS: printer='${request.printerName}', tickets=${request.tickets.size}, format=${request.pageFormat.width}x${request.pageFormat.height}")
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
            try {
                printer.printTickets(tickets)
                System.err.println("[${LocalDateTime.now()}] TICKETS SUCCESS: printer='${request.printerName}'")
            } catch (e: Exception) {
                System.err.println("[${LocalDateTime.now()}] TICKETS FAILED: printer='${request.printerName}', error=${e.javaClass.name}: ${e.message}")
                e.printStackTrace()
            }
        }
        return Response()
    }
}