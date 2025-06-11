package titulacion.backend.service

import org.springframework.stereotype.Service
import titulacion.backend.model.SessionActivityResult
import titulacion.backend.repository.SessionActivityResultRepository
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class SessionActivityResultService(
    private val repository: SessionActivityResultRepository
) {
    fun save(result: SessionActivityResult): SessionActivityResult = repository.save(result)

    fun getBySessionId(sesionId: Long): List<SessionActivityResult> =
        repository.findBySesionId(sesionId)

    fun getByUserId(userId: Long): List<SessionActivityResult> =
        repository.findByUserId(userId)

    fun getBySessionAndUser(sesionId: Long, userId: Long): List<SessionActivityResult> =
        repository.findBySesionIdAndUserId(sesionId, userId)
    fun calculateNormalizedAverage(sesionId: Long, userId: Long): BigDecimal {
        val results = repository.findBySesionIdAndUserId(sesionId, userId)

        if (results.isEmpty()) return BigDecimal.ZERO

        val totalNormalized = results.map { result ->
            val maxScore = result.activity.maxScore
            val rawScore = result.result

            if (maxScore != null && rawScore != null && maxScore > BigDecimal.ZERO) {
                rawScore.divide(maxScore, 4, RoundingMode.HALF_UP)
            } else {
                BigDecimal.ZERO
            }
        }.reduce { acc, curr -> acc.add(curr) }

        val average = totalNormalized.divide(BigDecimal(results.size), 4, RoundingMode.HALF_UP)

        return average.multiply(BigDecimal.TEN) // Promedio sobre 10
    }


}
