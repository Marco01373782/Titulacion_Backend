package titulacion.backend.repository


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import titulacion.backend.model.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByRoles(roles: String): List<User>
    fun findByEmail(email: String): User?
}
