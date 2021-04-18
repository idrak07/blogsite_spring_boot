package com.myblog.intern.model;

import javax.persistence.*;

@Entity
@Table(name= "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="user_id")
    private Integer userId;
    @Column(name="role")
    private String role;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="birth_date")
    private Integer birthDate;
    @Column(name="birth_month")
    private Integer birthMonth;
    @Column(name="birth_year")
    private Integer birthYear;
    @Column(name="street")
    private String street;
    @Column(name="state")
    private String state;
    @Column(name="country")
    private String country;
    @Column(name="zip_code")
    private Integer zipCode;
    @Column(name="contact")
    private String contact;
    @Column(name="profile_pic")
    private String profilePic;

    public UserDetails() {
    }

    public UserDetails(Integer id, Integer userId, String role, String firstName, String lastName, String email, Integer birthDate, Integer birthMonth, Integer birthYear, String street, String state, String country, Integer zipCode, String contact, String profilePic) {
        this.id = id;
        this.userId = userId;
        this.role = role;
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
    }
    public UserDetails(Integer userId, String role, String firstName, String lastName, String email){
        this.userId=userId;
        this.role= role;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", userId=" + userId +
                ", role='" + role + '\'' +
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
                '}';
    }
}
