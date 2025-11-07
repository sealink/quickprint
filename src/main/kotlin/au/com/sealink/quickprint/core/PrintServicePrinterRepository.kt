package au.com.sealink.quickprint.core

import au.com.sealink.printing.ticketprinter.PrintServiceLocator
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import javax.print.PrintService

@Repository
class PrintServicePrinterRepository : PrinterRepository {
    override fun requestPrinter(name: String): Printer {
        System.err.println("[${LocalDateTime.now()}] PrinterRepository REQUEST: printer='$name'")
        return TicketPrinter(name)
    }

    override fun findAll(): Iterable<PrintService> {
        System.err.println("[${LocalDateTime.now()}] PrinterRepository ENUM START")
        val services = PrintServiceLocator().all.toList()
        System.err.println("[${LocalDateTime.now()}] PrinterRepository ENUM FOUND: count=${services.size}, names=${services.joinToString(",") { it.name }}")
        return services
    }
}