package com.ThankSen.BaiTap2.Payload;

public class CreateDto {
    private boolean status;
    private String messenger;
    private Object object;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public CreateDto(boolean status, String messenger, Object object) {
        this.status = status;
        this.messenger = messenger;
        this.object = object;
    }
}
