package se.lexicon.g49marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;//this will hold HTML content

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Constructor

    public Advertisement(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @PrePersist
    public void dateCreatedAndExpired() {
        creationDate = LocalDateTime.now();
        expirationDate = creationDate.plusDays(30);
    }

    // Helper method to update expiration date
    public void updateExpirationDate(int days) {
        expirationDate = LocalDateTime.now().plusDays(days);
    }

    //Helper method to check if advertisement is expired
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }

    public void addUser(User user) {
        this.user = user;

    }

    public void removeUser(User user) {
        this.user = null;
    }
}
