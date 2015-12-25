package view;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloWorld", eager = true)
public class Home {
   public Home() {
      System.out.println("HelloWorld started!");
   }
   public String getMessage() {
      return "Hello World!";
   }
}