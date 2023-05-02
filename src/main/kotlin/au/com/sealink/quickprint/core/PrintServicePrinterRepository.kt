package au.com.sealink.quickprint.core

import au.com.sealink.printing.ticketprinter.PrintServiceLocator
import org.springframework.stereotype.Repository
import javax.print.PrintService

@Repository
class PrintServicePrinterRepository : PrinterRepository {
    override fun requestPrinter(name: String): Printer {
        return TicketPrinter(name)
    }

    override fun findAll(): Iterable<PrintService> {
        return PrintServiceLocator().all.toList()
    }
}