// DifficultyService.kt
package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.Difficulty
import titulacion.backend.repository.DifficultyRepository

@Service
class DifficultyService {

    @Autowired
    lateinit var repository: DifficultyRepository

    fun list(): List<Difficulty> = repository.findAll()

    fun getById(id: Long): Difficulty? = repository.findById(id).orElse(null)

    fun create(difficulty: Difficulty): Difficulty = repository.save(difficulty)

    fun delete(id: Long) {
        if (repository.existsById(id)) repository.deleteById(id)
    }
}
