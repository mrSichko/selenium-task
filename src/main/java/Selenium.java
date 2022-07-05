
import entity.Comment;
import page.AuthPage;
import page.Chat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Selenium {
    private final WebDriver WEB_DRIVER;

    public Selenium(WebDriver webDriver) {
        this.WEB_DRIVER = webDriver;
    }

    public static void setSystemProp() {
        System.setProperty(MyUtils.getProperty("webDriver.key")
                , MyUtils.getProperty("webDriver.value"));
    }

    public void start() throws InterruptedException {
        WEB_DRIVER.get(MyUtils.getProperty("selenium.url"));
        Thread.sleep(10000);
        signIn();
        selectChannel();
        sendComment(MyUtils.getRandomComment(new CommentDao().getAllComments()));
    }

    private void signIn() throws InterruptedException {
        AuthPage authPage = new AuthPage(MyUtils.getProperty("authPage.country"),
                MyUtils.getProperty("authPage.number"));

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"auth-qr-form\"]/div/button[1]")).click();
        Thread.sleep(500);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"auth-phone-number-form\"]/div/form/div[1]")).click();
        Thread.sleep(1000);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"sign-in-phone-code\"]")).clear();
        Thread.sleep(1000);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"sign-in-phone-code\"]")).sendKeys(authPage.getCountry());
        Thread.sleep(1000);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"auth-phone-number-form\"]/div/form/div[1]/div[2]/div[2]")).click();
        Thread.sleep(500);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"sign-in-phone-number\"]")).sendKeys(authPage.getPhoneNumber());
        Thread.sleep(500);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"auth-phone-number-form\"]/div/form/button[1]/div")).click();
        Thread.sleep(1000);

        System.out.println("Please enter code from sms:");
        String code = MyUtils.getCodeFromSMS();
        //потрібно провірку на правельність
        WEB_DRIVER.findElement(By.xpath("//*[@id=\"sign-in-code\"]")).sendKeys(code);
        Thread.sleep(2000);
    }

    private void selectChannel() throws InterruptedException {
        WEB_DRIVER.findElement(By.xpath("//*[@id=\"telegram-search-input\"]")).click();
        Thread.sleep(1000);

        Chat chat = new Chat(MyUtils.getProperty("chat.name"));

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"telegram-search-input\"]")).sendKeys(chat.getName());
        Thread.sleep(5000);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"LeftColumn-main\"]/div[2]/div[2]/div/div[2]/div/div/div[2]/div[1]/div/div/div[1]")).click();
        Thread.sleep(1000);
    }

    private void sendComment(Comment comment) throws InterruptedException {
        try {
            WEB_DRIVER.findElement(By.xpath("//*[@id=\"MiddleColumn\"]/div[3]/div[3]/div/button")).click();
            Thread.sleep(1000);
        } catch (Exception ex) {
            System.out.println("already at the bottom");
        }

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"message467\"]/div[3]/div/div[2]")).click();
        Thread.sleep(500);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"editable-message-text\"]")).click();
        Thread.sleep(500);

        WEB_DRIVER.findElement(By.xpath("//*[@id=\"editable-message-text\"]")).sendKeys(comment.getContent());
        Thread.sleep(500);
        WEB_DRIVER.findElement(By.xpath("//*[@id=\"MiddleColumn\"]/div[3]/div[2]/div[2]/div[2]/div/button")).click();
    }
}
