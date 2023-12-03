package command.domain

import command.infrastructure.exception.BusinessException

// Data class because of Axon Aggregate lateinit ussage
// TODO: 30.11.2023 check this again.
data class SafeId private constructor(val value: String) {
    companion object {
        operator fun invoke(value: String): SafeId {
            validate(value)
            return SafeId(value)
        }

        private fun validate(value: String) {
            check(value.trim().length >= 2) {
                throw SafeIdNotValidException(value)
            }
            check(value.trim().length <= 40) {
                throw SafeIdNotValidException(value)
            }
        }
    }
    override fun toString() = value
}

class SafeIdNotValidException(value: String) : BusinessException("AggregateId [${value}] doesn't match business case")
