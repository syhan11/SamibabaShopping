package com.example.demo;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="User_Data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="email", nullable=false)
    @NonNull
    @Size (min=3, max=30)
    private String email;

    @Column(name="password")
    @NonNull
    @Size(min=8)
    private String password;

    @Column(name="first_name")
    @NonNull
    @Size(min=3, max=30)
    private String firstName;

    @Column(name="last_name")
    @NonNull
    @Size(min=3, max=30)
    private String lastName;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="username")
    @NonNull
    @Size(min=3, max=30)
    private String username;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="state")
    @Size(min=2, max=2)
    private String state;

    @Column(name="zipcode")
    private int zipcode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),
               inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "orduser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<OrderHistory> orderhistory;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Card> cards;

    public User() {
    }

    public User(@Size(min = 3, max = 30) String email,
                @Size(min = 8) String password,
                @Size(min = 3, max = 30) String firstName,
                @Size(min = 3, max = 30) String lastName,
                boolean enabled,
                @Size(min = 3, max = 30) String username) {
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEnabled(enabled);
        this.setUsername(username);
        this.setAddress(null);
        this.setCity(null);
        this.setState(null);
        this.setZipcode(0);
        this.setRoles(null);
        this.setOrderhistory(null);
        this.setCards(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public Set<OrderHistory> getOrderhistory() {
        return orderhistory;
    }

    public void setOrderhistory(Set<OrderHistory> orderhistory) {
        this.orderhistory = orderhistory;
    }

    public boolean hasAuthority(String privilage) {
        boolean found = false;

        for (Role tmp : getRoles()) {
            if (tmp.getRole().equalsIgnoreCase(privilage)) {
                    found = true;
                    break;
            }
         }

        return found;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
}
