package servlet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.example.connectionpool;
import org.example.userconnector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/aa")
public class UserServlet extends HttpServlet {
    public static String StudentId;
    public static String Password;
    private connectionpool connectionPool;
    private Gson gson = new Gson();
    userconnector userconnector=new userconnector();
    @Override
    public void init() throws ServletException {
        connectionPool = new connectionpool(
                "jdbc:mysql://localhost:3306/db02",
                "root",
                "lwnznxf5555"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> responseMap = new HashMap<>();
        PrintWriter out = response.getWriter();

        try {
            StringBuilder jsonBuffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }

            JsonObject requestJson = JsonParser.parseString(jsonBuffer.toString()).getAsJsonObject();
            String action = requestJson.get("action").getAsString();
            if ("login".equals(action)) {
                try {
                    String username = requestJson.get("username").getAsString();
                    String password = requestJson.get("password").getAsString();
                    StudentId =username;
                    Password=password;
                    userconnector.login(username,password);
                    responseMap.put("status", "success");
                    responseMap.put("message", "Login successful");
                    out.print(gson.toJson(responseMap));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    responseMap.put("error", "Invalid credentials");
                    out.print(gson.toJson(responseMap));
                }
            } else if ("register".equals(action)) {
                try {
                    String username = requestJson.get("username").getAsString();
                    String password = requestJson.get("password").getAsString();
                    String email = requestJson.get("email").getAsString();
                    userconnector.register(Integer.parseInt(username), password, email, 1);
                    responseMap.put("status", "success");
                    responseMap.put("message", "Login successful");
                    out.print(gson.toJson(responseMap));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    responseMap.put("error", "Registration failed: " + e.getMessage());
                    out.print(gson.toJson(responseMap));
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseMap.put("error", "Invalid action");
                out.print(gson.toJson(responseMap));
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseMap.put("error", "Invalid request format");
            out.print(gson.toJson(responseMap));
        } finally {
            out.close();
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        resp.setContentType("application/json");
        resp.getWriter().print("{\"error\":\"GET method not supported\"}");
    }



    @Override
    public void destroy() {
        if (connectionPool != null) {
            try {
                connectionPool.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}