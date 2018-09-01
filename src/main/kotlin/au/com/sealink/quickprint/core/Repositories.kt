package au.com.sealink.quickprint.core

import org.springframework.data.repository.Repository
import javax.print.PrintService

interface PrinterRepository : Repository<PrintService, String> {
    fun findAll(): Iterable<PrintService>
    fun requestPrinter(name: String) : Printer
}
