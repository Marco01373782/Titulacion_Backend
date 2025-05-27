package titulacion.backend.repository


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.Sesion

@Repository
interface SesionRepository : JpaRepository<Sesion, Long>
