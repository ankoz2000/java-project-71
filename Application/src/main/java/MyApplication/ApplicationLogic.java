package MyApplication;

public class ApplicationLogic {
    SimpleConsoleApplication simpleConsoleApplication = new SimpleConsoleApplication();
        public void runApplication() {

        try {
            simpleConsoleApplication.runApplication();
        } catch (Exception e){
            System.out.println(e);
        }

    }
}
