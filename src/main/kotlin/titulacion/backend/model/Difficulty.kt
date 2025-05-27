package titulacion.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "difficulty")
class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name_difficulty")
    var name: String? = null
}
