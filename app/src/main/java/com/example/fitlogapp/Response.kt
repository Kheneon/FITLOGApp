package com.example.fitlogapp

enum class ResponseStatus{
    RS_SUCCESS, RS_FAILURE
}

class Response(rStatus : ResponseStatus, rPayload: String = "") {
    var status : ResponseStatus = ResponseStatus.RS_SUCCESS
    var payload : String = ""

    init {
        status = rStatus
        payload = rPayload
    }
}