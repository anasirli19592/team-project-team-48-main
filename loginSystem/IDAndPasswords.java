package loginSystem;

import java.util.HashMap;

public class IDAndPasswords {
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    protected HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }
}