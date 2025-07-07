package titulacion.backend.model

import jakarta.persistence.*
import titulacion.backend.enums.Difficulty
import titulacion.backend.enums.StatusSesion
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

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    var difficulty: Difficulty? = null

    var description: String? = null
}
