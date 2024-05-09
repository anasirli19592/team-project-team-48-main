package loginSystem;

public class Main {
    public static void main(String[] args) {
        
        IDAndPasswords idAndPasswords = new IDAndPasswords();

        LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo());
    }
}
