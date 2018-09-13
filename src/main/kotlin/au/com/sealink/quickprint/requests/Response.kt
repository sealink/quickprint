package au.com.sealink.quickprint.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class Response(@JsonProperty("status") val status :String = "OK")