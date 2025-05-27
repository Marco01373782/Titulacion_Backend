package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.dto.PatientDTO
import titulacion.backend.model.Patient
import titulacion.backend.repository.GenderRepository
import titulacion.backend.repository.PatientRepository
import titulacion.backend.repository.UserRepository

@Service
class PatientService {

    @Autowired
    lateinit var patientRepository: PatientRepository

    @Autowired
    lateinit var genderRepository: GenderRepository

    @Autowired
    lateinit var userRepository: UserRepository

    fun list(): List<Patient> {
        return patientRepository.findAll()
    }

    fun getById(id: Long): Patient? {
        return patientRepository.findById(id).orElse(null)
    }

    fun create(dto: PatientDTO): Patient {
        // Validar que el usuario no tenga ya un paciente
        val existingPatient = patientRepository.findByUserId(dto.userId)
        if (existingPatient != null) {
            throw IllegalStateException("Este usuario ya tiene un paciente registrado.")
        }

        val gender = genderRepository.findById(dto.genderId)
            .orElseThrow { IllegalArgumentException("Género no encontrado") }

        val user = userRepository.findById(dto.userId)
            .orElseThrow { IllegalArgumentException("Usuario no encontrado") }

        val patient = Patient().apply {
            firstname = dto.firstname
            secondname = dto.secondname
            surname = dto.surname
            age = dto.age
            photoUrl = dto.photoUrl
            this.gender = gender
            this.user = user
        }

        return patientRepository.save(patient)
    }

    fun delete(id: Long) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id)
        }
    }
}
