package ucsal.foneflex.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.dto.usuario.UsuarioRequestDTO;
import ucsal.foneflex.dto.usuario.UsuarioResponseDTO;
import ucsal.foneflex.model.Usuario;
import ucsal.foneflex.repository.UsuarioRepository;

@Service
@Validated
@RequiredArgsConstructor
public class UsuarioService
{   
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuarioRequestDTO)
    {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setSenha(bCryptPasswordEncoder.encode(usuarioRequestDTO.senha()));
        usuario.setAtivo(usuarioRequestDTO.ativo());

        Usuario savedUsuario = usuarioRepository.save(usuario);

        return toResponseDTO(savedUsuario);
    }

    public List<UsuarioResponseDTO> readAUsuarios()
    {
        return usuarioRepository.findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public UsuarioResponseDTO updateUsuario(Long id, @Valid UsuarioRequestDTO usuarioRequestDTO)
    {
        Usuario updateUsuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        updateUsuario.setNome(usuarioRequestDTO.nome());
        updateUsuario.setEmail(usuarioRequestDTO.email());
        updateUsuario.setSenha(bCryptPasswordEncoder.encode(usuarioRequestDTO.senha()));
        updateUsuario.setAtivo(usuarioRequestDTO.ativo());

        return toResponseDTO(usuarioRepository.save(updateUsuario));
    }

    public void deleteUsuario(Long id)
    {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuarioRepository.deleteById(usuario.getId());
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario)
    {
        return new UsuarioResponseDTO
        (
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getAtivo()
        );
    }
}
