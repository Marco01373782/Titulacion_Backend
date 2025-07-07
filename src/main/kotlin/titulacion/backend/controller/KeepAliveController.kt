package titulacion.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class KeepAliveController {

    @GetMapping("/ping")
    fun keepAlive(): ResponseEntity<String> {
        return ResponseEntity.ok("pong")
    }
}
