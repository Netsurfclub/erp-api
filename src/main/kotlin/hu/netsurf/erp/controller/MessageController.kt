package hu.netsurf.erp.controller

import hu.netsurf.erp.model.Message
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class MessageController {

    @QueryMapping
    fun getMessage(): Message {
        return Message(id = 1, text = "Hello, World.")
    }
}
