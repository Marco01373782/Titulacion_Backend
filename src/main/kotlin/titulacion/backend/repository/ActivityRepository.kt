package titulacion.backend.repository


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.Activity

@Repository
interface ActivityRepository : JpaRepository<Activity, Long>{
    fun findByDifficultyIdAndTypeId(difficultyId: Long, typeId: Long): List<Activity>

}
