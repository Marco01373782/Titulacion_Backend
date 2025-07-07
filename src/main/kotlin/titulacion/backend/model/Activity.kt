package titulacion.backend.model

import jakarta.persistence.*
import titulacion.backend.enums.ActivityType
import titulacion.backend.enums.Difficulty
import java.math.BigDecimal

@Entity
@Table(name = "activity")
class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var title: String? = null
    var description: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    var type: ActivityType? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    var difficulty: Difficulty? = null

    @Column(name = "resource_url")
    var resourceUrl: String? = null

    @Column(name = "max_score", nullable = false)
    var maxScore: BigDecimal = BigDecimal(100)

}
