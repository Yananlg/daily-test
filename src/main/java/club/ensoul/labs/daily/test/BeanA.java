package club.ensoul.labs.daily.test;

public class BeanA {
    
    private String userName;
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    BeanB beanConvert(Convert<BeanB> convert) {
        return convert.convert();
    }
    
    @Override
    public String toString() {
        return "BeanA{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
