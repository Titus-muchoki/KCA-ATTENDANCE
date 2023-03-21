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

import static spark.Spark.*;

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

        get("/courses/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Course> courses = courseDao.getAll(); //refresh list of links for navbar
            model.put("courses", courses);
            return new ModelAndView(model, "course-form.hbs"); //new layout
        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new category

        post("/courses", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            Course newCause = new Course(name);
            courseDao.add(newCause);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        get("/courses/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            courseDao.clearAllCourses();
            studentDao.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

//        //get: delete all infections

        get("/students/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            studentDao.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/courses/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfCourseToFind = Integer.parseInt(req.params("id")); //new
            Course foundCourse = courseDao.findById(idOfCourseToFind);
            model.put("course", foundCourse);
            List<Student> allStudentByCourses = courseDao.getAllStudentsByCourse(idOfCourseToFind);
            model.put("students", allStudentByCourses);
            model.put("courses", courseDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "course-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());

//        //get: show a form to update a category

        get("/courses/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editCourse", true);
            Course course = courseDao.findById(Integer.parseInt(req.params("id")));
            model.put("course", course);
            model.put("courses", courseDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "course-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/courses/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfCourseToEdit = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("newCourseName");
            courseDao.update(idOfCourseToEdit, newName);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

//        //get: delete an individual viral test

        get("/courses/:course_id/students/:student_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfStudentToDelete = Integer.parseInt(req.params("student_id"));
            studentDao.deleteById(idOfStudentToDelete);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/students/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Course> courses = courseDao.getAll();
            model.put("courses", courses);
            return new ModelAndView(model, "student-form.hbs");
        }, new HandlebarsTemplateEngine());

//        //task: process new viral form
        post("/students", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            List<Course> allCourses = courseDao.getAll();
            model.put("courses", allCourses);
            String name = req.queryParams("name");
            String reg = req.queryParams("reg");
            String email = req.queryParams("email");
            String tel = req.queryParams("tel");
            String unit = req.queryParams("unit");
            String lecture = req.queryParams("lecture");
            String dateTaught = req.queryParams("dateTaught");
            String studentRemark = req.queryParams("studentRemark");
            int courseId = Integer.parseInt(req.queryParams("courseId"));
            Student newStudent = new Student(name,reg,email,tel,unit,lecture,dateTaught,studentRemark,courseId);        //See what we did with the hard coded categoryId?
            studentDao.add(newStudent);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
