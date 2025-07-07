package titulacion.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.SessionActivityResult

@Repository
interface SessionActivityResultRepository : JpaRepository<SessionActivityResult, Long> {
    fun findBySesionId(sesionId: Long): List<SessionActivityResult>
    fun findByUserId(userId: Long): List<SessionActivityResult>
    fun findBySesionIdAndUserId(sesionId: Long, userId: Long): List<SessionActivityResult>
}
