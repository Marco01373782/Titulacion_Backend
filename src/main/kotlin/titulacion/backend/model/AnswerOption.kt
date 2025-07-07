package titulacion.backend.model

import jakarta.persistence.*

import com.fasterxml.jackson.annotation.JsonBackReference

@Entity
@Table(name = "answer_option")
class AnswerOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference  // <-- Aquí para que no vuelva a serializar pregunta
    var question: Question? = null

    @Column(name = "answer_text", nullable = false)
    var answerText: String? = null

    @Column(name = "is_correct", nullable = false)
    var isCorrect: Boolean = false
}

