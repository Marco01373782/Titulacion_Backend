package titulacion.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "activity")
class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var title: String? = null
    var description: String? = null

    @ManyToOne
    @JoinColumn(name = "type_id")
    var type: ActivityType? = null

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    var difficulty: Difficulty? = null

    @Column(name = "resource_url")
    var resourceUrl: String? = null
}
