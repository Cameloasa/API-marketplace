package se.lexicon.g49marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private boolean isExpired;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Constructor

    public Advertisement(String title, String description, LocalDateTime creationDate, LocalDateTime expirationDate, User user) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.expirationDate = LocalDateTime.now().plusDays(30);
        this.user = user;
    }

    @PrePersist
    public void dateCreatedAndExpired() {
        creationDate = LocalDateTime.now();
        expirationDate = creationDate.plusDays(30);
    }

    //Helper method to check if advertisement is expired
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }




}
