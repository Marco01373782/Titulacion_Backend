package titulacion.backend.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import titulacion.backend.model.Gender
import titulacion.backend.service.GenderService

@RestController
@RequestMapping("/gender")
class GenderController {

    @Autowired
    lateinit var genderService: GenderService

    @GetMapping
    fun list(): List<Gender> {
        return genderService.list()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Gender? {
        return genderService.getById(id)
    }

    @PostMapping
    fun create(@RequestBody gender: Gender): Gender {
        return genderService.create(gender)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        genderService.delete(id)
    }
}
