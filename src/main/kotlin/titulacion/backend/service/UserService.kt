package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import titulacion.backend.model.User
import titulacion.backend.repository.UserRepository
import titulacion.backend.repository.ParentescoRepository

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var parentescoRepository: ParentescoRepository

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
        // Validar que el correo no esté en uso
        if (!validateEmail(user.email!!)) {
            throw IllegalArgumentException("Email ya está registrado")
        }

        // Validar la fuerza de la contraseña
        if (!validatePassword(user.password!!)) {
            throw IllegalArgumentException("La contraseña no cumple con los requisitos de seguridad")
        }

        // Si el usuario tiene un parentesco_id, buscar el objeto completo en la DB
        user.parentesco?.id?.let { parentescoId ->
            val parentesco = parentescoRepository.findById(parentescoId).orElse(null)
            user.parentesco = parentesco
        }

        // Si el rol es nulo o vacío, asignar "user" por defecto
        if (user.roles.isNullOrBlank()) {
            user.roles = "user"
        }

        // Encriptar la contraseña antes de guardar
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
