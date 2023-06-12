package utils.authorisation.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.fwred.model.entities.AppUsersEntity;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {
    public String token;
    public String username;
    public Set<Roles> roles;
    public BigDecimal orgId;
}
