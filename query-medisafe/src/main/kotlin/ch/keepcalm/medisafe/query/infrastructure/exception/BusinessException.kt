package ch.keepcalm.medisafe.query.infrastructure.exception

open class BusinessException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
