package au.com.sealink.quickprint.requests

import au.com.sealink.printing.ticket_printer.TicketElement
import com.fasterxml.jackson.annotation.JsonProperty

data class Ticket(
    @JsonProperty("type") val type: String,
    @JsonProperty("x") val x: Double,
    @JsonProperty("y") val y: Double,
    @JsonProperty("value") val value: String,
    @JsonProperty("key") val key: String,
    @JsonProperty("font_size") val fontSize: Int,
    @JsonProperty("italic") val isItalic: Boolean,
    @JsonProperty("bold") val isBold: Boolean,
    @JsonProperty("orientation") val orientation: String
)

fun Ticket.toTicketElement() : TicketElement {
    val element = TicketElement()
    element.fontSize = this.fontSize
    element.isBold = this.isBold
    element.isItalic = this.isItalic
    element.value = this.value
    element.x = this.x.toInt()
    element.y = this.y.toInt()
    return element
}