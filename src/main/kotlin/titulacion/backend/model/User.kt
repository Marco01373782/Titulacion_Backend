package titulacion.backend.model

import jakarta.persistence.*
import titulacion.backend.enums.Parentesco
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var email: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Column(name = "first_name")
    var firstName: String? = null

    @Column(name = "last_name")
    var lastName: String? = null

    var roles: String? = "user"

    @Enumerated(EnumType.STRING)
    @Column(name = "parentesco")
    var parentesco: Parentesco? = null


    @Column(name = "created_at")
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = LocalDateTime.now()
}
