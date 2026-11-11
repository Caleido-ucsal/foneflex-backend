package ucsal.foneflex.config.security;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ucsal.foneflex.model.Usuario;

@Configuration
public class JpaAuditingConfig
{
    @Bean
    public AuditorAware<Usuario> auditorAware()
    {
        return () -> {
            Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null
                    || !authentication.isAuthenticated()
                    || authentication instanceof AnonymousAuthenticationToken) {
                return Optional.empty();
            }

            Object principal = authentication.getPrincipal();

            if (principal instanceof UsuarioPrincipal usuarioPrincipal) {
                return Optional.of(usuarioPrincipal.getUsuario());
            }

            return Optional.empty();
        };
    }
}
