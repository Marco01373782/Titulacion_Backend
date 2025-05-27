package titulacion.backend.model


import jakarta.persistence.*
@Entity
@Table(name = "parentesco")
class Parentesco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name_parentesco")
    var nameParentesco: String? = null
}

