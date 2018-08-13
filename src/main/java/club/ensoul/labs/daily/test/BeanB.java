package club.ensoul.labs.daily.test;

public class BeanB {
    
    private String userName;
    
    public BeanB() {
    }
    
    public BeanB(BeanA beanA) {
        this.userName = beanA.getUserName();
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Override
    public String toString() {
        return "BeanB{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
