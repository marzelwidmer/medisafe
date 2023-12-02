package ch.keepcalm.command.medisafe.infrastructure.exception

open class BusinessException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
