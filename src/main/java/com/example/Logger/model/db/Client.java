package com.example.Logger.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    public Client(String username, String password, String email, Boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String username;
    private String password;
    private String email;
    private Boolean isAdmin;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Log> log;

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
