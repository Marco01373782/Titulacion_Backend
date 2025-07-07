package titulacion.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import titulacion.backend.dto.QuestionRequest
import titulacion.backend.service.QuestionService

@RestController
@RequestMapping("/api/questions")
class QuestionController(
    private val questionService: QuestionService
) {

    @PostMapping("/{activityId}")
    fun createQuestion(
        @PathVariable activityId: Long,
        @RequestBody request: QuestionRequest
    ): ResponseEntity<Any> {
        val saved = questionService.createQuestionWithAnswers(
            activityId,
            request.questionText,
            request.answers
        )
        return ResponseEntity.ok(saved)
    }

    @GetMapping("/activity/{activityId}")
    fun getQuestionsByActivity(@PathVariable activityId: Long): ResponseEntity<Any> {
        val questions = questionService.getQuestionsByActivity(activityId)
        return ResponseEntity.ok(questions)
    }
    @PutMapping("/{questionId}")
    fun updateQuestion(
        @PathVariable questionId: Long,
        @RequestBody request: QuestionRequest
    ): ResponseEntity<Any> {
        val updated = questionService.updateQuestionWithAnswers(
            questionId,
            request.questionText,
            request.answers
        )
        return ResponseEntity.ok(updated)
    }


}
