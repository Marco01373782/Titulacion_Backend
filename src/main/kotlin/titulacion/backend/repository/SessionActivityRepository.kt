package titulacion.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.SessionActivity
import titulacion.backend.model.SessionActivityId

@Repository
interface SessionActivityRepository : JpaRepository<SessionActivity, SessionActivityId>{
    fun findBySesionId(sesionId: Long): List<SessionActivity>
}
