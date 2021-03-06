package com.chrish.paymentsystem.paymentorchestratorcomms.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.chrish.paymentsystem.queuecomms.message.QueueMessage;

@XmlRootElement(name = "paymentOutMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentOutMessage implements QueueMessage {

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "result")
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PaymentOutMessage{" +
                "id='" + id + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
