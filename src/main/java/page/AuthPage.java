package page;
public class AuthPage {
    private String country ;
    private String phoneNumber;

    public AuthPage(String country, String phoneNumber) {
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
