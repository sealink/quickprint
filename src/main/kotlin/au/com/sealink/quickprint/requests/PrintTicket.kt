package au.com.sealink.quickprint.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class PrintTicket(
    @JsonProperty("api_key") val apiKey: String,
    @JsonProperty("printer_name") val printerName: String,
    @JsonProperty("page_format") val pageFormat: PageFormat,
    @JsonProperty("tickets") val tickets: List<List<Ticket>>
)