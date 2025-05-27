
package titulacion.backend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import titulacion.backend.model.Difficulty
import titulacion.backend.service.DifficultyService

@RestController
@RequestMapping("/difficulties")
class DifficultyController {

    @Autowired
    lateinit var service: DifficultyService

    @GetMapping
    fun list(): List<Difficulty> = service.list()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Difficulty? = service.getById(id)

    @PostMapping
    fun create(@RequestBody difficulty: Difficulty): Difficulty = service.create(difficulty)

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}
