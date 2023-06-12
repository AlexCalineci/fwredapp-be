package utils.authorisation.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import utils.authorisation.model.Roles;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class AppUser {

    public String userName;
    public String password;
    public Set<Roles> roles;
    public BigDecimal orgId;

}