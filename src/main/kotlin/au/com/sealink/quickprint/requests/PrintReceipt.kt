package au.com.sealink.quickprint.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class PrintReceipt(
    @JsonProperty("printer_name") val printerName: String,
    @JsonProperty("tickets") val tickets: List<List<ReceiptTicket>>
)