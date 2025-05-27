package titulacion.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "activity_type")
class ActivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name_type")
    var name: String? = null
}
