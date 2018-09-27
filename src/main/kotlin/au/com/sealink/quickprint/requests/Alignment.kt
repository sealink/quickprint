package au.com.sealink.quickprint.requests

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.fasterxml.jackson.annotation.JsonProperty

enum class Alignment {
    @JsonEnumDefaultValue
    Unknown,
    @JsonProperty("left")
    Left,
    @JsonProperty("right")
    Right,
    @JsonProperty("center")
    Center
}