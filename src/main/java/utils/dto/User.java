package utils.dto;

public class User {

    private final String login;
    private final String password;

    public User() {
        this.login = "DefaultPersonalUser";
        this.password = "1q2w3e4r";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
