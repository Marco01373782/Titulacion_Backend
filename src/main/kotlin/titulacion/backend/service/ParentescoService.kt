package titulacion.backend.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.Parentesco
import titulacion.backend.repository.ParentescoRepository

@Service
class ParentescoService {

    @Autowired
    lateinit var parentescoRepository: ParentescoRepository

    fun list(): List<Parentesco> {
        return parentescoRepository.findAll()
    }

    fun getById(id: Long): Parentesco? {
        return parentescoRepository.findById(id).orElse(null)
    }

    fun create(parentesco: Parentesco): Parentesco {
        return parentescoRepository.save(parentesco)
    }

    fun delete(id: Long) {
        if (parentescoRepository.existsById(id)) {
            parentescoRepository.deleteById(id)
        }
    }
}
