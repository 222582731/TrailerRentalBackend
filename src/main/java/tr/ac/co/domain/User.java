package tr.ac.co.domain;

import jakarta.persistence.*;
import tr.ac.co.domain.enums.Role;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;


    //Automatically set the createdAt.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public User(){

    }

    private User(Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.createdAt = builder.createdAt;
    }

    //Getters
    public Long getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public Role getRole(){
        return role;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setRole(Role role){
        this.role = role;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }

public static class Builder{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime createdAt;

    public Builder setId(Long id){
        this.id = id;
        return this;
    }
    public Builder setFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public Builder setLastName(String lastName){
        this.lastName = lastName;
        return this;
    }
    public Builder setEmail(String email){
        this.email = email;
        return this;
    }
    public Builder setPassword(String password){
        this.password = password;
        return this;
    }
    public Builder setRole(Role role){
        this.role = role;
        return this;
    }
    public Builder setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
        return this;
    }

    public Builder copy(User user){
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.password = user.password;
        this.role = user.role;
        this.createdAt = user.createdAt;
        return this;
    }

    public User build(){
    return new User(this);
    }

}
}
