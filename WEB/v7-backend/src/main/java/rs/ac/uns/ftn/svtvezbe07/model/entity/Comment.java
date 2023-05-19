package rs.ac.uns.ftn.svtvezbe07.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Boolean deleted;
    @OneToMany( fetch = FetchType.LAZY)
    private Set<Reaction> Reaction= new HashSet<Reaction>();
}
