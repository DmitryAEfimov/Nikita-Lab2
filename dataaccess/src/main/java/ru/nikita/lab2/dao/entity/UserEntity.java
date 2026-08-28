package ru.nikita.lab2.dao.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Audited;

import org.hibernate.annotations.Generated;
import ru.nikita.lab2.api.enumeration.Gender;
import ru.nikita.lab2.api.enumeration.HairColor;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
@Audited
@Audited.Table(name = "users_aud")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "login", nullable = false, updatable = false, unique = true)
    private String login;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private int age;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "hair_color")
    private HairColor hairColor;
    @ManyToOne
    @JoinTable(name = "user_friends", joinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private UserEntity owner;
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Audited.CollectionTable(name = "user_friends_aud")
    private Set<UserEntity> friends;

    protected UserEntity() {
        // for jpa only
    }

    private UserEntity(UUID id, String login, String name, int age, Gender gender, HairColor hairColor) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hairColor = hairColor;
        this.friends = Set.of();
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * @return immutable set of user's friends
     */
    public Set<UserEntity> getFriends() {
        return Set.copyOf(friends);
    }

    public void setFriends(Set<UserEntity> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        var user = (UserEntity) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Builder {
        private UUID id;
        private String login;
        private String name;
        private int age;
        private Gender gender;
        private HairColor hairColor;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder hairColor(HairColor hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, login, name, age, gender, hairColor);
        }
    }
}
