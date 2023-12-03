package ch.keepcalm.medisafe.command.infrastructure.exception

open class BusinessException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
