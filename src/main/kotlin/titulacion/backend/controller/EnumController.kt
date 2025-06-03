package titulacion.backend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import titulacion.backend.enums.ActivityType
import titulacion.backend.enums.Difficulty
import titulacion.backend.enums.Gender
import titulacion.backend.enums.Parentesco

@RestController
@RequestMapping("/enums")
class EnumController {

    @GetMapping("/difficulties")
    fun getDifficulties(): List<String> {
        return Difficulty.values().map { it.name }
    }

    @GetMapping("/activity-types")
    fun getActivityTypes(): List<String> {
        return ActivityType.values().map { it.name }
    }
    @GetMapping("/gender")
    fun getGenders(): List<String> {
        return Gender.values().map { it.name }
    }
    @GetMapping("/parentesco")
    fun getParentesco(): List<String> {
        return Parentesco.values().map { it.name }
    }
}
