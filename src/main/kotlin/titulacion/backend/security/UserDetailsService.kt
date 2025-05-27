package titulacion.backend.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import titulacion.backend.repository.UserRepository

@Service
class UserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("Usuario no encontrado")

        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            emptyList() // puedes agregar roles aquí
        )
    }
}
