package titulacion.backend.dto

data class PatientDTO(
    var firstname: String,
    var secondname: String,
    var surname: String,
    var age: Int,
    var photoUrl: String,
    var genderId: Long,
    var userId: Long
)
