package titulacion.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import titulacion.backend.enums.StatusSesion
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "sesion_usuario")
class SesionUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "sesion_id", nullable = false)
    var sesion: Sesion? = null

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: StatusSesion = StatusSesion.BLOQUEADA

    @Column(name = "started_at")
    var startedAt: LocalDateTime? = null

    @Column(name = "ended_at")
    var endedAt: LocalDateTime? = null

    @Column(name = "session_duration_seconds")
    var sessionDurationSeconds: Int? = null

    @Column(precision = 5, scale = 2)
    var result: BigDecimal? = null


    var mode: String? = null

    var date: LocalDate? = null
}
