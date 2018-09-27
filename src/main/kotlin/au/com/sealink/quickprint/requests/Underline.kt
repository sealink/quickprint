package au.com.sealink.quickprint.requests

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.fasterxml.jackson.annotation.JsonProperty

enum class Underline {
    @JsonEnumDefaultValue
    Unknown,
    @JsonProperty("none")
    None,
    @JsonProperty("single")
    Single,
    @JsonProperty("double")
    Double,
}