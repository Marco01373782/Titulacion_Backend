package titulacion.backend.model

import jakarta.persistence.*
import titulacion.backend.enums.Difficulty
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "sesion")
class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "patient_id")
    var patient: Patient? = null

    var title: String? = null
    var date: LocalDate? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    var difficulty: Difficulty? = null

    var result: String? = null
    var mode: String? = null
    var description: String? = null

    @Column(name = "session_duration_seconds")
    var sessionDurationSeconds: Int? = null

    @Column(name = "started_at")
    var startedAt: LocalDateTime? = null

    @Column(name = "ended_at")
    var endedAt: LocalDateTime? = null
}
