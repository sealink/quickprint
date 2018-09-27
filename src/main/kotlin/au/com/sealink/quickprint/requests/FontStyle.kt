package au.com.sealink.quickprint.requests

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.fasterxml.jackson.annotation.JsonProperty

enum class FontStyle {
    @JsonEnumDefaultValue
    Unknown,
    @JsonProperty("normal")
    Normal,
    @JsonProperty("emphasized")
    Empahsized,
    @JsonProperty("inverted")
    Inverted,
    @JsonProperty("inverted_emphasized")
    InvertedEmpahsized
}