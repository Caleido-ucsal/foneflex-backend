package ucsal.foneflex.usuario;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.usuario.dto.UsuarioRequestDTO;
import ucsal.foneflex.usuario.dto.UsuarioResponseDTO;

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

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> readAUsuarios()
    {
        return ResponseEntity.ok(usuarioService.readAUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDTO usuarioRequestDTO)
    {
        usuarioService.updateUsuario(id, usuarioRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Usuário atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluirUsuario(@PathVariable Integer id)
    {
        usuarioService.deleteUsuario(id);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "O usuário foi deletado com sucesso"));
    }
}
