package titulacion.backend.model

import java.io.Serializable

data class SessionActivityId(
    var sesion: Long? = null,
    var activity: Long? = null
) : Serializable
