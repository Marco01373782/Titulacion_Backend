package titulacion.backend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import titulacion.backend.dto.PatientDTO
import titulacion.backend.model.Patient
import titulacion.backend.service.PatientService

@RestController
@RequestMapping("/patients")
class PatientController {

    @Autowired
    lateinit var patientService: PatientService

    @GetMapping
    fun list(): List<Patient> {
        return patientService.list()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Patient? {
        return patientService.getById(id)
    }

    @PostMapping
    fun create(@RequestBody dto: PatientDTO): Patient {
        return patientService.create(dto)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) {
        patientService.delete(id)
    }
}
