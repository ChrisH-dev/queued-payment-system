package com.chrish.paymentsystem.paymentorchestratorcomms.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.chrish.paymentsystem.queuecomms.message.QueueMessage;

@XmlRootElement(name = "paymentInMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentInMessage implements QueueMessage {

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "cardNumber")
    private String cardNumber;
    @XmlAttribute(name = "cvc")
    private String cvc;

    @XmlAttribute(name = "amount")
    private Long amount;
    @XmlAttribute(name = "currencyCode")
    private String currencyCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "PaymentInMessage{" +
                "id='" + id + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvc='" + cvc + '\'' +
                ", amount=" + amount +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
