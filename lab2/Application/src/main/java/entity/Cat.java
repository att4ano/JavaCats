package entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @Column(name = "breed")
    private String breed;

    @Column(name = "color")
    private String color;

    @Setter
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "friends",
            joinColumns = {@JoinColumn(name = "first_friend_id")},
            inverseJoinColumns = {@JoinColumn(name = "second_friend_id")}
    )
    private Set<Cat> catFriends;
}
