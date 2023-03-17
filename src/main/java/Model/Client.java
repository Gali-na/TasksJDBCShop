package Model;

public class Client {
    private int Id;
    private String name;
    private String surname;
    private  String phoneNumber;
    private  String pasword;
    private  String email;

    public Client() {
    }

    public Client( String name, String surname, String phoneNumber, String pasword, String email) {
        Client client = new Client();
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.pasword = pasword;
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pasword='" + pasword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
