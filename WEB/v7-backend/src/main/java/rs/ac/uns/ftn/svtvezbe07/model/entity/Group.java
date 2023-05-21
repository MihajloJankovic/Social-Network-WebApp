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
@Table(name = "groupa")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String Description;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private Long GroupAdmin;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<post> posts = new HashSet<post>();
    @Column(nullable = true)
    private Boolean deleted;
}
