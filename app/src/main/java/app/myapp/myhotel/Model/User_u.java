package app.myapp.myhotel.Model;

public class User_u {


    private String token;

    private String name;

    public User_u() {

    }



    public User_u(String token,String name) {
        this.token = token;
        this.name=name;
    }

    public User_u(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
