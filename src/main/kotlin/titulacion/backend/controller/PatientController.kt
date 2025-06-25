package titulacion.backend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    @GetMapping("/by-user/{userId}")
    fun getPatientDTOByUserId(@PathVariable userId: Long): ResponseEntity<PatientDTO> {
        return try {
            val dto = patientService.getPatientDTOByUserId(userId)
            ResponseEntity.ok(dto)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/by-user-full/{userId}")
    fun getFullPatientByUserId(@PathVariable userId: Long): ResponseEntity<Patient> {
        return try {
            val patient = patientService.getFullPatientByUserId(userId)
            ResponseEntity.ok(patient)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    @PostMapping
    fun create(@RequestBody dto: PatientDTO): Patient {
        return patientService.create(dto)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) {
        patientService.delete(id)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: PatientDTO): ResponseEntity<Patient> {
        return try {
            val updatedPatient = patientService.update(id, dto)
            ResponseEntity.ok(updatedPatient)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

}
