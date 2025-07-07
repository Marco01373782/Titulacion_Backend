package titulacion.backend.repository



import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.Question

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
    fun findByActivityId(activityId: Long): List<Question>
}
