package com.example.hotel_booking.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.example.hotel_booking.util.UserUtil.*;

@Getter
@Setter
@NoArgsConstructor
public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = MAX_SIZE_NAME)
    private String name;

    @Email
    @NotBlank
    @Size(max = MAX_SIZE_EMAIL)
    private String email;

    @Size(min = MIN_SIZE_PASSWORD, max = MAX_SIZE_PASSWORD)
    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
