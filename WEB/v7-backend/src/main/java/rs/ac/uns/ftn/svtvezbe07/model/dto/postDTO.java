package rs.ac.uns.ftn.svtvezbe07.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.svtvezbe07.model.entity.Comment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class postDTO {
    private String user;
    private String tekst;
    private String dat;
    private Long id;
    private int like;
    private int dislike;
    private int hearth;
    private Set<Comment> comments;
}
