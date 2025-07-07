package titulacion.backend.dto

import titulacion.backend.model.AnswerOption

data class QuestionRequest(
    val questionText: String,
    val answers: List<AnswerOption>
)
