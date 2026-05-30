package co.edu.cesde.selection.infrastructure.rest;

import co.edu.cesde.selection.application.AuthService;
import co.edu.cesde.selection.domain.Reclutador;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public static class LoginRequest {
        public String email;
        public String password;
    }

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginRequest req) {
        Map<String,Object> res = new HashMap<>();
        Reclutador r = authService.login(req.email, req.password);
        if (r == null) {
            res.put("success", false);
            res.put("message", "Credenciales inválidas");
            return res;
        }
        Map<String,Object> user = new HashMap<>();
        user.put("id", r.getId());
        user.put("nombre", r.getNombre());
        user.put("apellido", r.getApellido());
        user.put("email", r.getEmail());

        Map<String,Object> data = new HashMap<>();
        data.put("user", user);

        res.put("success", true);
        res.put("data", data);
        return res;
    }

    @GetMapping("/profile")
    public Map<String,Object> profile(@RequestParam(required = false) Long userId) {
        Map<String,Object> res = new HashMap<>();
        if (userId == null) {
            res.put("success", false);
            res.put("message", "userId requerido");
            return res;
        }
        Reclutador r = authService.getProfile(userId);
        if (r == null) {
            res.put("success", false);
            res.put("message", "No encontrado");
            return res;
        }
        Map<String,Object> user = new HashMap<>();
        user.put("id", r.getId());
        user.put("nombre", r.getNombre());
        user.put("apellido", r.getApellido());
        user.put("email", r.getEmail());

        Map<String,Object> data = new HashMap<>();
        data.put("user", user);

        res.put("success", true);
        res.put("data", data);
        return res;
    }
}
