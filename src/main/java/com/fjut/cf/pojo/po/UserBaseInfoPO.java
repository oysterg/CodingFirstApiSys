package com.fjut.cf.pojo.po;

import java.util.Date;

/**
 * 用户基础信息实体类
 *
 * @author axiang [2019/10/11]
 */
public class UserBaseInfoPO {
    private Integer id;
    private String username;
    private String nick;
    private Integer gender;
    private String email;
    private String phone;
    private String motto;
    private Date registerTime;
    private Integer rating;
    private Integer ranking;
    private Integer acNum;
    private Integer acb;
    private String school;
    private String faculty;
    private String major;
    private String cla;
    private String studentId;
    private String graduationYear;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getAcNum() {
        return acNum;
    }

    public void setAcNum(Integer acNum) {
        this.acNum = acNum;
    }

    public Integer getAcb() {
        return acb;
    }

    public void setAcb(Integer acb) {
        this.acb = acb;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    @Override
    public String toString() {
        return "UserBaseInfoPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nick='" + nick + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", motto='" + motto + '\'' +
                ", registerTime=" + registerTime +
                ", rating=" + rating +
                ", ranking=" + ranking +
                ", acNum=" + acNum +
                ", school='" + school + '\'' +
                ", faculty='" + faculty + '\'' +
                ", major='" + major + '\'' +
                ", cla='" + cla + '\'' +
                ", studentId='" + studentId + '\'' +
                ", graduationYear='" + graduationYear + '\'' +
                '}';
    }
}
