package titulacion.backend.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "session_activity_result")
data class SessionActivityResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "sesion_id", nullable = false)
    val sesion: Sesion,

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    val activity: Activity,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(precision = 5, scale = 2)
    val result: BigDecimal? = null,


    @Column(name = "completed_at")
    val completedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "duration_seconds")
    val durationSeconds: Int? = null
)
