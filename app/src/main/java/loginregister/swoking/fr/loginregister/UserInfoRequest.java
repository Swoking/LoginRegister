package loginregister.swoking.fr.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class UserInfoRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://192.168.1.63:8080/LoginRegister/getAllImage.inc.php";
    private Map<String, String> params;

    public UserInfoRequest(int id, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id", String.valueOf(id));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
