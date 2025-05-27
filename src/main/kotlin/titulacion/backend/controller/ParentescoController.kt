package titulacion.backend.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import titulacion.backend.model.Parentesco
import titulacion.backend.service.ParentescoService

@RestController
@RequestMapping("/parentesco")
class ParentescoController {

    @Autowired
    lateinit var parentescoService: ParentescoService
    @GetMapping
    fun list(): List<Parentesco> {
        return parentescoService.list()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Parentesco? {
        return parentescoService.getById(id)
    }

    @PostMapping
    fun create(@RequestBody parentesco: Parentesco): Parentesco {
        return parentescoService.create(parentesco)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) {
        parentescoService.delete(id)
    }
}
