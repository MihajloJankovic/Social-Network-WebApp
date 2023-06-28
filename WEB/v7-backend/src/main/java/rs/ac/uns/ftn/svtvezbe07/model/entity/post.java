package rs.ac.uns.ftn.svtvezbe07.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user;

    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private LocalDateTime date;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<Comment>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Reaction> reactions = new HashSet<Reaction>();
    @Column(nullable = true)
    private Boolean deleted;
}
