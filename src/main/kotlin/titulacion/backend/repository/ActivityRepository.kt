package titulacion.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.Activity
import titulacion.backend.enums.ActivityType
import titulacion.backend.enums.Difficulty

@Repository
interface ActivityRepository : JpaRepository<Activity, Long> {

    fun findByDifficultyAndType(difficulty: Difficulty, type: ActivityType): List<Activity>

    fun findByDifficulty(difficulty: Difficulty): List<Activity>

    fun findByType(type: ActivityType): List<Activity>
}
