package rs.ac.uns.ftn.svtvezbe07.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GroupReturnDTO {

    private String name;
    private String description;
    private String date;
}
