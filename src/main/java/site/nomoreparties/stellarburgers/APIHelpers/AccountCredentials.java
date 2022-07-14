package site.nomoreparties.stellarburgers.APIHelpers;

public class AccountCredentials {
    private final String email;
    private final String password;

    public AccountCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
