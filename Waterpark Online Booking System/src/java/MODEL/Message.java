package MODEL;

public class Message {
    private int messageID;
    private String messageName;
    private String messageEmail;
    private String messageSubject;
    private String messageDescription;

    public Message(){

    }
    
    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageEmail() {
        return messageEmail;
    }

    public void setMessageEmail(String messageEmail) {
        this.messageEmail = messageEmail;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }
    
}
