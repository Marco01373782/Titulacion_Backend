
package titulacion.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.ActivityType

@Repository
interface ActivityTypeRepository : JpaRepository<ActivityType, Long>
