package titulacion.backend.model



import jakarta.persistence.*

@Entity
@Table(name = "session_activity")
@IdClass(SessionActivityId::class)
class SessionActivity {
    @Id
    @ManyToOne
    @JoinColumn(name = "sesion_id")
    var sesion: Sesion? = null

    @Id
    @ManyToOne
    @JoinColumn(name = "activity_id")
    var activity: Activity? = null
}
