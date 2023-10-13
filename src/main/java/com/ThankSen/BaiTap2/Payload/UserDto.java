package com.ThankSen.BaiTap2.Payload;

public class UserDto {
    private String email;
    private String fullname;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private String website;
    private String facebook;
    private String roleName;

    public UserDto(String email, String fullname, String password, String avatar, String phone, String address, String website, String facebook, String roleName) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.website = website;
        this.facebook = facebook;
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
