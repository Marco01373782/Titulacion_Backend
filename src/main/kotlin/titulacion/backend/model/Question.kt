package titulacion.backend.model

import jakarta.persistence.*

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
@Table(name = "question")
class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    @JsonBackReference  // <-- Aquí, porque es la referencia "hijo"
    var activity: Activity? = null

    @Column(name = "question_text", nullable = false)
    var questionText: String? = null

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonManagedReference  // <-- Aquí, es la referencia "padre"
    var answerOptions: MutableList<AnswerOption> = mutableListOf()
}
