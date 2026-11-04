package ucsal.foneflex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.dto.usuario.UsuarioRequestDTO;
import ucsal.foneflex.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController
{
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<Object, String>> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO)
    {
        usuarioService.createUsuario(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuário criado com sucesso"));
    }

    // @GetMapping
    // public ResponseEntity<List<UsuarioRequestDTO>> getAllUsuarios()
    // {
    //     List<UsuarioRequestDTO> usuarios = usuarioService.getAllUsuarios();
    //     return ResponseEntity.ok(usuarios);
    // }
}
