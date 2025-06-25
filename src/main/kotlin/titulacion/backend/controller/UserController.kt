package titulacion.backend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import titulacion.backend.model.User
import titulacion.backend.security.JwtUtil
import titulacion.backend.service.SesionUsuarioService
import titulacion.backend.service.UserService

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var jwtUtil: JwtUtil // Inyectamos JwtUtil
    @Autowired
    lateinit var sesionUsuarioService: SesionUsuarioService


    @GetMapping
    fun list(): List<User> {
        return userService.list()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): User? {
        return userService.getById(id)
    }

    @PostMapping
    fun create(@RequestBody user: User): ResponseEntity<Any> {
        return try {
            val createdUser = userService.create(user)

            // 🔁 Asignar sesiones después de crear usuario
            sesionUsuarioService.assignAllExistingSessionsToUser(createdUser.id!!)

            ResponseEntity.ok(createdUser)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }


    @PostMapping("/login")
    fun login(@RequestBody loginData: Map<String, String>): ResponseEntity<Any> {
        val email = loginData["email"]
        val password = loginData["password"]

        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            return ResponseEntity.badRequest().body("Email y contraseña son requeridos")
        }

        // Buscar al usuario por su correo electrónico
        val user = userService.findByEmail(email)
            ?: return ResponseEntity.status(401).body("Usuario no encontrado")

        val encoder = org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()

        // Verificar la contraseña
        return if (encoder.matches(password, user.password)) {
            // Generar el JWT con el email en lugar del username
            val token = jwtUtil.generateToken(user.email!!)
            ResponseEntity.ok(
                mapOf(
                    "token" to token,
                    "email" to user.email!!,
                    "roles" to user.roles!!,
                    "id" to user.id
                )
            )
// Devolver el token al cliente
        } else {
            ResponseEntity.status(401).body("Contraseña incorrecta")
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<User> {
        val updatedUser = userService.update(id, user)
        return if (updatedUser != null) {
            ResponseEntity.ok(updatedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) {
        userService.delete(id)
    }
}
