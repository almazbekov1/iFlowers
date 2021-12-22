package in.flowers.db.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserRequest {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
