package titulacion.backend.service

import org.springframework.stereotype.Service
import titulacion.backend.model.SessionActivityResult
import titulacion.backend.repository.SessionActivityResultRepository

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
}
