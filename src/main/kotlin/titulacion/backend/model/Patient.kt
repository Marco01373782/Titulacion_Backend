package titulacion.backend.model

import jakarta.persistence.*
import titulacion.backend.enums.Gender

@Entity
@Table(name = "patient")
class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var firstname: String? = null
    var secondname: String? = null
    var surname: String? = null
    var age: Int? = null

    @Column(name = "photo_url")
    var photoUrl: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    var gender: Gender? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
}
