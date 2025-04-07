package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.StudentConnector;
import org.example.connectionpool;
import org.example.courseConnector;
import org.example.userconnector;

import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/admin")
public class adminServlet extends HttpServlet {
    String userid = UserServlet.StudentId;
    String Password = UserServlet.Password;
    private connectionpool connectionPool;
    private Gson gson = new Gson();
    org.example.userconnector userconnector = new userconnector();

    @Override
    public void init() {
        connectionPool = new connectionpool(
                "jdbc:mysql://localhost:3306/db02",
                "root",
                "lwnznxf5555"
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        courseConnector courseConnector = new courseConnector(connectionPool);
        Map<String, String> responseMap = new HashMap<>();
        PrintWriter out = resp.getWriter();
        try {
            String action = req.getParameter("action");
            if (action == null) {
                resp.setStatus(400);
                resp.getWriter().print("{\"error\":\"Missing action parameter\"}");
                return;
            }

            if ("getStudentImformation".equals(action)) {
                StudentConnector studentConnector = new StudentConnector();
                out.print(gson.toJson(studentConnector.getStudentImformation()));
                out.flush();
            } else if ("getCourseImformation".equals(action)) {
                out.print(gson.toJson(courseConnector.getCourseImformation()));
                out.flush();
            }
             else if ("seecoursesByid".equals(action)) {
                String id=req.getParameter("id");
                out.print(gson.toJson(courseConnector.getCourseByid(Integer.parseInt(id))));
                out.flush();
            } else if (
                    "getStudentBycourse".equals(action)) {
                String id=req.getParameter("id");
                out.print(gson.toJson(courseConnector.getCourseByid(Integer.parseInt(id))));
                out.flush();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        courseConnector courseConnector = new courseConnector(connectionPool);
        Map<String, String> responseMap = new HashMap<>();
        PrintWriter out = resp.getWriter();
        try {
            StringBuilder jsonBuffer = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
            JsonObject requestJson = JsonParser.parseString(jsonBuffer.toString()).getAsJsonObject();
            String action = requestJson.get("action").getAsString();
            if ("updatePhone".equals(action)) {
                StudentConnector studentConnector = new StudentConnector();
                String phonenember=req.getParameter("newPhone");
                String id=req.getParameter("id");
                studentConnector.updatePhonenumber(Integer.parseInt(id),Integer.parseInt(phonenember));
                out.print("修改成功");
                out.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}