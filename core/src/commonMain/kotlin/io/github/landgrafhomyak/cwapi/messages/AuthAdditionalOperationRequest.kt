package io.github.landgrafhomyak.cwapi.messages

import io.github.landgrafhomyak.cwapi.json.toJson
import io.github.landgrafhomyak.cwapi.types.Operation
import io.github.landgrafhomyak.cwapi.types.Token

class AuthAdditionalOperationRequest(
    val token: Token,
    val operation: Operation,
) : Message.Client2Server {
    override fun toJson() = """{"token":${this.token.toJson()},"action":"authAdditionalOperation","payload":{"operation":${this.operation.toJson()}}}"""
}