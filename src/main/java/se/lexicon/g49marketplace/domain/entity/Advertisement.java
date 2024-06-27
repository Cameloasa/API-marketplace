package se.lexicon.g49marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


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


    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;//this will hold HTML content

    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false, updatable = false)
    private LocalDateTime expirationDate;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Constructor

    public Advertisement(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        expirationDate = creationDate.plusDays(30);
    }


    }






