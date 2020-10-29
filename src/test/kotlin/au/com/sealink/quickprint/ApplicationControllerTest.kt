package au.com.sealink.quickprint

import au.com.sealink.printing.ticket_printer.TicketPageSettings
import au.com.sealink.quickprint.core.Printer
import au.com.sealink.quickprint.core.PrinterRepository
import au.com.sealink.quickprint.requests.PageFormat
import au.com.sealink.quickprint.requests.PrintTicket
import au.com.sealink.quickprint.requests.Ticket
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import javax.print.DocFlavor
import javax.print.DocPrintJob
import javax.print.PrintService
import javax.print.ServiceUIFactory
import javax.print.attribute.Attribute
import javax.print.attribute.AttributeSet
import javax.print.attribute.PrintServiceAttribute
import javax.print.attribute.PrintServiceAttributeSet
import javax.print.event.PrintServiceAttributeListener
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockitokotlin2.whenever


@ExtendWith(SpringExtension::class)
@WebMvcTest
class ApplicationControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var printerRepository: PrinterRepository

    @Test
    fun `Show Version`() {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("1.0.0-TEST"))
    }

    @Test
    fun `List Printers`() {
        val printer1 = TestPrintService("Printer 1")
        val printer2 = TestPrintService("Printer 2")

        whenever(printerRepository.findAll()).thenReturn(listOf(printer1, printer2))

        mockMvc.perform(get("/printers").header("x-api-key", "TEST"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0]").value("Printer 1"))
                .andExpect(jsonPath("\$.[1]").value("Printer 2"))
    }

    @Test
    fun `Print Tickets`() {
        whenever(printerRepository.requestPrinter("printer-1")).thenReturn(TestPrinter())

        val mapper = jacksonObjectMapper()
        val request = PrintTicket(
                "TEST",
                "printer-1",
                PageFormat(1.0, 1.0, null),
                ArrayList<ArrayList<Ticket>>()
                )
        mockMvc.perform(post("/print-tickets")
                .header("x-api-key", "TEST")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk)
    }
}

class TestPrinter : Printer {
    override fun setTicketPageSettings(settings: TicketPageSettings) {
    }

    override fun printTickets(tickets: List<au.com.sealink.printing.ticket_printer.Ticket>) {
    }
}

class TestPrintService(private val name: String): PrintService {

    override fun getName(): String {
        return this.name
    }

    override fun removePrintServiceAttributeListener(listener: PrintServiceAttributeListener?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDefaultAttributeValue(category: Class<out Attribute>?): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSupportedDocFlavors(): Array<DocFlavor> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAttributes(): PrintServiceAttributeSet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getServiceUIFactory(): ServiceUIFactory {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAttributeValueSupported(attrval: Attribute?, flavor: DocFlavor?, attributes: AttributeSet?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSupportedAttributeValues(category: Class<out Attribute>?, flavor: DocFlavor?, attributes: AttributeSet?): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSupportedAttributeCategories(): Array<Class<*>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : PrintServiceAttribute?> getAttribute(category: Class<T>?): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addPrintServiceAttributeListener(listener: PrintServiceAttributeListener?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUnsupportedAttributes(flavor: DocFlavor?, attributes: AttributeSet?): AttributeSet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPrintJob(): DocPrintJob {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAttributeCategorySupported(category: Class<out Attribute>?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isDocFlavorSupported(flavor: DocFlavor?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
