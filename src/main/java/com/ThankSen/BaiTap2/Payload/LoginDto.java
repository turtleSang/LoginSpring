package com.ThankSen.BaiTap2.Payload;

public class LoginDto {
    private boolean check;
    private String token;
    private String messenger;

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginDto(boolean check, String token, String messenger) {
        this.check = check;
        this.token = token;
        this.messenger = messenger;
    }

}
