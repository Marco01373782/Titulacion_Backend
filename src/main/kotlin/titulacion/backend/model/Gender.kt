package titulacion.backend.model


import jakarta.persistence.*

@Entity
@Table(name = "gender")
class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name_gender")
    var nameGender: String? = null
}
