package au.com.sealink.quickprint.requests

import au.com.sealink.printing.ticketprinter.Justification
import au.com.sealink.printing.ticketprinter.TicketElement
import com.fasterxml.jackson.annotation.JsonProperty

data class ReceiptTicket(
    @JsonProperty("type") val type: ElementType,
    @JsonProperty("value") val value: String,
    @JsonProperty("font_style") val fontStyle: FontStyle?,
    @JsonProperty("underline") val underline: Underline?,
    @JsonProperty("alignment") val alignment: Alignment?
)

fun ReceiptTicket.toTicketElement() : TicketElement {
    val element = TicketElement()

    if (this.fontStyle != null) {
        element.isInverted = this.fontStyle == FontStyle.Inverted || this.fontStyle == FontStyle.InvertedEmpahsized
        element.isBold = this.fontStyle == FontStyle.Empahsized || this.fontStyle == FontStyle.InvertedEmpahsized
    }

    when (this.underline ?: Underline.None) {
        Underline.Unknown -> element.underline = au.com.sealink.printing.ticketprinter.Underline.None
        Underline.None -> element.underline = au.com.sealink.printing.ticketprinter.Underline.None
        Underline.Single -> element.underline = au.com.sealink.printing.ticketprinter.Underline.Single
        Underline.Double -> element.underline = au.com.sealink.printing.ticketprinter.Underline.Double
    }

    when (this.alignment ?: Alignment.Left) {
        Alignment.Unknown -> element.justification = Justification.LEFT
        Alignment.Left -> element.justification = Justification.LEFT
        Alignment.Right -> element.justification = Justification.RIGHT
        Alignment.Center -> element.justification = Justification.CENTRE
    }

    element.value = when (type) {
        ElementType.EmptyLine -> System.lineSeparator()
        ElementType.Image -> "imageBase64:" + this.value
        else -> value
    }

    return element
}