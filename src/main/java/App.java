import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Selenium.setSystemProp();
        Selenium selenium = new Selenium(new ChromeDriver());
        selenium.start();
    }

}
