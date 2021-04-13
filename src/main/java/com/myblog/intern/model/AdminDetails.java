package com.myblog.intern.model;

public class AdminDetails {
    private Integer id;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer birthDate;
    private Integer birthMonth;
    private Integer birthYear;
    private String street;
    private String state;
    private String country;
    private Integer zipCode;
    private String contact;
    private String profilePic;
    private double salary;

    public AdminDetails() {
    }

    public AdminDetails(Integer id, Integer userId, String firstName, String lastName, String email, Integer birthDate, Integer birthMonth, Integer birthYear, String street, String state, String country, Integer zipCode, String contact, String profilePic, double salary) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.street = street;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.contact = contact;
        this.profilePic = profilePic;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Integer birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "AdminDetails{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", birthMonth=" + birthMonth +
                ", birthYear=" + birthYear +
                ", street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode=" + zipCode +
                ", contact='" + contact + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", salary=" + salary +
                '}';
    }
}
