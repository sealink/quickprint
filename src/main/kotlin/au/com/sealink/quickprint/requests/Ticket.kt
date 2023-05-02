package au.com.sealink.quickprint.requests

import au.com.sealink.printing.ticketprinter.TicketElement
import com.fasterxml.jackson.annotation.JsonProperty

data class Ticket(
    @JsonProperty("type") val type: String,
    @JsonProperty("x") val x: Double,
    @JsonProperty("y") val y: Double,
    @JsonProperty("width") val width: Int?,
    @JsonProperty("height") val height: Int?,
    @JsonProperty("value") val value: String,
    @JsonProperty("key") val key: String,
    @JsonProperty("font_size") val fontSize: Double,
    @JsonProperty("italic") val isItalic: Boolean,
    @JsonProperty("bold") val isBold: Boolean,
    @JsonProperty("orientation") val orientation: String
)

fun Ticket.toTicketElement() : TicketElement {
    val element = TicketElement()
    element.fontSize = this.fontSize.toInt()
    element.isBold = this.isBold
    element.isItalic = this.isItalic
    element.value = this.value
    element.x = this.x.toInt()
    element.y = this.y.toInt()
    element.width = this.width
    element.height = this.height
    return element
}