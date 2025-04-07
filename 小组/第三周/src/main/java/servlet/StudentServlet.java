package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.connectionpool;
import org.example.courseConnector;
import org.example.userconnector;

import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/stu")
public class StudentServlet extends HttpServlet {
    String StudentId = UserServlet.StudentId;
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
        PrintWriter out = resp.getWriter();

        try {
            String action = req.getParameter("action");

            if ("seeCourse".equals(action)) {
                try {
                    out.print(gson.toJson(courseConnector.seeCourse(0)));
                    out.flush();
                } catch (SQLException ex) {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print("{\"error\":\"数据库错误\"}");
                }
            } else if ("seeSelectedCourse".equals(action)) {
                try {
                    out.print(gson.toJson(courseConnector.seeSelectedCourse(Integer.parseInt(StudentId))));
                } catch (SQLException e) {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print("{\"error\":\"数据库错误\"}");
                }
            }

            else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"无效的action参数\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"服务器内部错误\"}");
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
            if ("selectCourse".equals(action)) {
                try {
                    String courseId = requestJson.get("courseId").getAsString();
                    courseConnector.selectcourse(Integer.parseInt(StudentId),Integer.parseInt(courseId));
                    if (courseId != null) {

                        out.print("{\"message\": \"选课成功，课程编号：" + courseId + "\"}");
                    } else {
                        out.print("{\"message\": \"课程编号无效，请重试。\"}");
                    }
                    out.flush();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }  else if ("selecttuixuancourse".equals(action)) {
                try {
                    String courseId = req.getParameter("courseId");
                    courseConnector.selecttuixuancourse(Integer.parseInt(StudentId),Integer.parseInt(courseId));
                    if (courseId != null) {

                        out.print("{\"message\": \"选课成功，课程编号：" + courseId + "\"}");
                    } else {
                        out.print("{\"message\": \"课程编号无效，请重试。\"}");
                    }
                    out.flush();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}