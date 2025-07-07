package titulacion.backend.dto

import titulacion.backend.enums.Gender

data class PatientDTO(
    var firstname: String?,
    var secondname: String?,
    var surname: String?,
    var age: Int?,
    var photoUrl: String?,
    var gender: Gender?,
    var userId: Long?
)

