package titulacion.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.AnswerOption

@Repository
interface AnswerOptionRepository : JpaRepository<AnswerOption, Long> {
    fun findByQuestionId(questionId: Long): List<AnswerOption>
}
