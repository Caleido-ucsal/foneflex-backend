package ucsal.foneflex.config.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.model.Usuario;

@Getter
@RequiredArgsConstructor
public class UsuarioPrincipal implements UserDetails
{
    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword()
    {
        return usuario.getSenha();
    }

    @Override
    public String getUsername()
    {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return Boolean.TRUE.equals(usuario.getAtivo());
    }
}
