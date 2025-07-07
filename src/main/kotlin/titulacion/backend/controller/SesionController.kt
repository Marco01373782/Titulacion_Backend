package titulacion.backend.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import titulacion.backend.model.Sesion
import titulacion.backend.service.SesionService

@RestController
@RequestMapping("/sesions")
class SesionController {

    @Autowired
    lateinit var sesionService: SesionService

    @GetMapping
    fun list(): List<Sesion> {
        return sesionService.list()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Sesion? {
        return sesionService.getById(id)
    }

    @PostMapping
    fun create(@RequestBody sesion: Sesion): Sesion {
        return sesionService.create(sesion)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        sesionService.delete(id)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody sesion: Sesion): Sesion {
        return sesionService.update(id, sesion)
    }

}
