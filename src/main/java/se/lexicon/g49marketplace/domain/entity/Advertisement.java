package se.lexicon.g49marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    @ManyToOne
    private User user;

    @OneToMany
    private List<Category> categories;
}
