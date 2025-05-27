package titulacion.backend.model

import jakarta.persistence.*

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

    @ManyToOne
    @JoinColumn(name = "gender_id")
    var gender: Gender? = null

    @ManyToOne
    @JoinColumn(name = "users_id")
    var user: User? = null
}
