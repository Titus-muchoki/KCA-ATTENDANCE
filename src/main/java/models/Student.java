package models;

import java.util.Objects;

public class Student {
    private String name;
    private String reg;
    private String email;
    private String tel;
    private String unit;
    private String lecture;
    private String dateTaught;
    private String studentRemark;

    public Student(String name, String reg, String email, String tel, String unit, String lecture, String dateTaught, String studentRemark) {
        this.name = name;
        this.reg = reg;
        this.email = email;
        this.tel = tel;
        this.unit = unit;
        this.lecture = lecture;
        this.dateTaught = dateTaught;
        this.studentRemark = studentRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(reg, student.reg) && Objects.equals(email, student.email) && Objects.equals(tel, student.tel) && Objects.equals(unit, student.unit) && Objects.equals(lecture, student.lecture) && Objects.equals(dateTaught, student.dateTaught) && Objects.equals(studentRemark, student.studentRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, reg, email, tel, unit, lecture, dateTaught, studentRemark);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getDateTaught() {
        return dateTaught;
    }

    public void setDateTaught(String dateTaught) {
        this.dateTaught = dateTaught;
    }

    public String getStudentRemark() {
        return studentRemark;
    }

    public void setStudentRemark(String studentRemark) {
        this.studentRemark = studentRemark;
    }
}
