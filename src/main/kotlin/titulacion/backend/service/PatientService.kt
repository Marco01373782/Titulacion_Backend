package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.dto.PatientDTO
import titulacion.backend.model.Patient
import titulacion.backend.repository.PatientRepository
import titulacion.backend.repository.UserRepository

@Service
class PatientService {

    @Autowired
    lateinit var patientRepository: PatientRepository

    @Autowired
    lateinit var userRepository: UserRepository

    fun list(): List<Patient> {
        return patientRepository.findAll()
    }


    fun create(dto: PatientDTO): Patient {
        val userId = dto.userId ?: throw IllegalArgumentException("El userId no puede ser null.")

        val existingPatient = patientRepository.findByUserId(userId)
        if (existingPatient != null) {
            throw IllegalStateException("Este usuario ya tiene un paciente registrado.")
        }

        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("Usuario no encontrado") }

        val patient = Patient().apply {
            firstname = dto.firstname
            secondname = dto.secondname
            surname = dto.surname
            age = dto.age
            photoUrl = dto.photoUrl
            gender = dto.gender
            this.user = user
        }

        return patientRepository.save(patient)
    }


    fun delete(id: Long) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id)
        }
    }
    fun getPatientDTOByUserId(userId: Long): PatientDTO {
        require(userId > 0) { "El ID de usuario debe ser mayor a cero." }

        val patient = patientRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("No se encontró un paciente con el userId: $userId")

        return PatientDTO(
            firstname = patient.firstname,
            secondname = patient.secondname,
            surname = patient.surname,
            age = patient.age,
            photoUrl = patient.photoUrl,
            gender = patient.gender,
            userId = patient.user?.id ?: throw IllegalStateException("El paciente no tiene usuario asignado")
        )

    }

    fun update(id: Long, dto: PatientDTO): Patient {
        val patient = patientRepository.findById(id).orElseThrow { IllegalArgumentException("Paciente no encontrado") }
        patient.firstname = dto.firstname
        patient.secondname = dto.secondname
        patient.surname = dto.surname
        patient.age = dto.age
        patient.gender = dto.gender
        patient.photoUrl = dto.photoUrl
        return patientRepository.save(patient)
    }


    fun getFullPatientByUserId(userId: Long): Patient {
        require(userId > 0) { "El ID de usuario debe ser mayor a cero." }

        return patientRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("No se encontró un paciente con el userId: $userId")
    }

}
