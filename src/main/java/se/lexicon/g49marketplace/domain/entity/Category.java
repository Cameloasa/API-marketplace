package se.lexicon.g49marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    private String description;

    @ManyToOne
    private Advertisement advertisement;

    public Category(String title) {
        this.title = title;
    }
}
