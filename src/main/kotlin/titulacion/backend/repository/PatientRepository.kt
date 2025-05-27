package titulacion.backend.repository


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.Patient

@Repository
interface PatientRepository : JpaRepository<Patient, Long>{
    fun findByUserId(userId: Long): Patient?
}
