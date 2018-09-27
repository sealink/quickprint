package au.com.sealink.quickprint.requests

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.fasterxml.jackson.annotation.JsonProperty

enum class ElementType {
    @JsonEnumDefaultValue
    Unknown,
    @JsonProperty("text")
    Text,
    @JsonProperty("image")
    Image,
    @JsonProperty("empty_line")
    EmptyLine,
    @JsonProperty("barcode")
    Barcode
}