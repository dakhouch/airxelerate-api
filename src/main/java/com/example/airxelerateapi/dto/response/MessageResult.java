package com.example.airxelerateapi.dto.response;
import com.example.airxelerateapi.enumeration.MessageStatus;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MessageResult implements Serializable {
    String messageContent;
    MessageStatus messageType;

    public MessageResult(String messageContent, MessageStatus messageType) {
        this.messageContent=messageContent;
        this.messageType=messageType;
    }

}
