package com.example.airxelerateapi.dto.response;
import com.example.airxelerateapi.enumeration.MessageStatus;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MessageResult implements Serializable {
    String messageContent;
    MessageStatus messageType;
    String field;

    private MessageResult(String messageContent, MessageStatus messageType,String field) {
        this.messageContent=messageContent;
        this.messageType=messageType;
        this.field=field;
    }

    public static MessageResult getMessageResultWithoutField(String messageContent, MessageStatus messageType){
        return new MessageResult(messageContent,messageType,null);
    }
    public static MessageResult getMessageResultWithField(String messageContent, MessageStatus messageType,String field) {
        return new MessageResult(messageContent, messageType,field);
    }

}
