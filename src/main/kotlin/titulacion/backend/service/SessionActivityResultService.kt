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

        results.forEach { r ->
            println("RESULTADO rawScore=${r.result} maxScore=${r.activity.maxScore}")
        }

        val normalizedScores = results.mapNotNull { result ->
            val maxScore = result.activity.maxScore
            val rawScore = result.result

            if (maxScore == null || rawScore == null || maxScore <= BigDecimal.ZERO) return@mapNotNull null

            val safeRawScore = if (rawScore > maxScore) maxScore else rawScore

            safeRawScore.divide(maxScore, 4, RoundingMode.HALF_UP)
        }

        normalizedScores.forEach {
            println("Normalized Score: $it")
        }

        if (normalizedScores.isEmpty()) return BigDecimal.ZERO

        val total = normalizedScores.reduce { acc, curr -> acc.add(curr) }
        val average = total.divide(BigDecimal(normalizedScores.size), 4, RoundingMode.HALF_UP)

        return average.multiply(BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)
    }





}
