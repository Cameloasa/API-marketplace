package se.lexicon.g49marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder


@Entity
public class User {
    @Id
    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Advertisement> advertisements = new HashSet<>();


    //constructor for database


    public User(String email, String password, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }



    //Helper methods for adding and removing advertisements
    public void addAdvertisement(Advertisement advertisement) {
        advertisements.add(advertisement);
        advertisement.setUser(this);
    }
    public void removeAdvertisement(Advertisement advertisement) {
        advertisements.remove(advertisement);
        advertisement.setUser(null);
    }
}
