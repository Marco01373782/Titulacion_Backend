package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.*
import titulacion.backend.repository.*

@Service
class QuestionService(
    @Autowired private val questionRepository: QuestionRepository,
    @Autowired private val answerOptionRepository: AnswerOptionRepository,
    @Autowired private val activityRepository: ActivityRepository // Asegúrate de tener este
) {

    fun createQuestionWithAnswers(
        activityId: Long,
        questionText: String,
        answers: List<AnswerOption>
    ): Question {
        val activity = activityRepository.findById(activityId)
            .orElseThrow { Exception("Activity not found") }

        val question = Question().apply {
            this.activity = activity
            this.questionText = questionText
        }

        val savedQuestion = questionRepository.save(question)

        answers.forEach { answer ->
            answer.question = savedQuestion
            answerOptionRepository.save(answer)
        }

        return savedQuestion
    }

    fun getQuestionsByActivity(activityId: Long): List<Question> {
        val questions = questionRepository.findByActivityId(activityId)
        questions.forEach { q ->
            q.answerOptions = answerOptionRepository.findByQuestionId(q.id!!)
                .toMutableList()
        }
        return questions
    }
    fun updateQuestionWithAnswers(
        questionId: Long,
        newText: String,
        newAnswers: List<AnswerOption>
    ): Question {
        val question = questionRepository.findById(questionId)
            .orElseThrow { Exception("Pregunta no encontrada") }

        // Actualizamos el texto de la pregunta
        question.questionText = newText
        questionRepository.save(question)

        // Eliminamos las respuestas anteriores
        answerOptionRepository.deleteAll(answerOptionRepository.findByQuestionId(questionId))

        // Guardamos las nuevas respuestas
        newAnswers.forEach { answer ->
            answer.question = question
            answerOptionRepository.save(answer)
        }

        // Retornamos la pregunta actualizada (ya con respuestas nuevas cargadas)
        question.answerOptions = answerOptionRepository.findByQuestionId(questionId).toMutableList()
        return question
    }


}
