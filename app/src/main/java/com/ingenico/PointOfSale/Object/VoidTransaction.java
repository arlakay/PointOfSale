package com.ingenico.PointOfSale.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by e_er_de on 06/08/2017.
 */

public class VoidTransaction {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("messages")
    @Expose
    private String messages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
