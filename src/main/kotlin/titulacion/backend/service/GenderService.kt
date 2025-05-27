package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.Gender
import titulacion.backend.repository.GenderRepository

@Service
class GenderService {

    @Autowired
    lateinit var genderRepository: GenderRepository

    fun list(): List<Gender> {
        return genderRepository.findAll()
    }

    fun getById(id: Long): Gender? {
        return genderRepository.findById(id).orElse(null)
    }

    fun create(gender: Gender): Gender {
        return genderRepository.save(gender)
    }

    fun delete(id: Long) {
        if (genderRepository.existsById(id)) {
            genderRepository.deleteById(id)
        }
    }
}
