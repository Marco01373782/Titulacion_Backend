package titulacion.backend.repository


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.SesionUsuario

@Repository
interface SesionUsuarioRepository : JpaRepository<SesionUsuario, Long> {
    fun findBySesionIdAndUserId(sesionId: Long, userId: Long): SesionUsuario?
    fun findAllByUserId(userId: Long): List<SesionUsuario>
}
