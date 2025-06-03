package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import titulacion.backend.model.User
import titulacion.backend.repository.UserRepository

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    // Obtener todos los usuarios
    fun list(): List<User> {
        return userRepository.findAll()
    }

    // Obtener un usuario por ID
    fun getById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    // Validar el correo electrónico
    fun validateEmail(email: String): Boolean {
        return userRepository.findByEmail(email) == null
    }

    // Validar la fuerza de la contraseña
    fun validatePassword(password: String): Boolean {
        // Debe tener al menos 8 caracteres, incluir letras mayúsculas, números y caracteres especiales
        val regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$"
        return password.matches(regex.toRegex())
    }

    fun create(user: User): User {
        if (!validateEmail(user.email!!)) {
            throw IllegalArgumentException("Email ya está registrado")
        }

        if (!validatePassword(user.password!!)) {
            throw IllegalArgumentException("La contraseña no cumple con los requisitos de seguridad")
        }

        // Ya no se busca el parentesco desde DB, simplemente se usa como enum directo
        if (user.roles.isNullOrBlank()) {
            user.roles = "user"
        }

        val encoder = BCryptPasswordEncoder()
        user.password = encoder.encode(user.password)

        return userRepository.save(user)
    }


    // Actualizar un usuario existente
    fun update(id: Long, user: User): User? {
        return if (userRepository.existsById(id)) {
            user.id = id
            userRepository.save(user)
        } else {
            null
        }
    }

    // Eliminar un usuario
    fun delete(id: Long) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
        }
    }

    // Consultar un usuario por email
    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}
