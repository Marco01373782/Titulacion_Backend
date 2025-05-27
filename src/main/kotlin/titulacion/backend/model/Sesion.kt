package titulacion.backend.model

import jakarta.persistence.*
import java.time.LocalDate

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

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    var difficulty: Difficulty? = null

    var result: String? = null
    var mode: String? = null
    var description: String? = null
}
