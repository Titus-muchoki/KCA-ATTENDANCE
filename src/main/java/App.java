import dao.Sql2oCourseDao;
import dao.Sql2oStudentDao;
import models.Course;
import models.Student;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
//        port(8090);
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/kca";      //connect to todolist, not todolist_test!

        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        Sql2oStudentDao studentDao = new Sql2oStudentDao(sql2o);
        Sql2oCourseDao courseDao = new Sql2oCourseDao(sql2o);

        //get: show all infections in all categories and show all categories

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Course> allCourses = courseDao.getAll();
            model.put("courses", allCourses);
            List<Student> students = studentDao.getAll();
            model.put("students", students);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
