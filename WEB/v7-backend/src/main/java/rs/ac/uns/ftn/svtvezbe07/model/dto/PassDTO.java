package rs.ac.uns.ftn.svtvezbe07.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassDTO {
    private String NewPassword;
    private String OldPassword;
    private String OldPassword2;

}