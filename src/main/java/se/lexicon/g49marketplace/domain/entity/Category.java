package se.lexicon.g49marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @Column(unique = true, nullable = false)
    private String description;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    private Set<Advertisement> advertisements;


    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //helper method to add/remove an advertisement
    public void addAdvertisement(Advertisement advertisement) {
        this.advertisements.add(advertisement);
        advertisement.setCategory(this);
    }
    public void removeAdvertisement(Advertisement advertisement) {
        this.advertisements.remove(advertisement);
        advertisement.setCategory(null);
    }
}
