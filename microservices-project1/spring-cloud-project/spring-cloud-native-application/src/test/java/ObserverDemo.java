import java.util.Observable;
import java.util.Observer;

/**
 * 2017 Spring Cloud Config Client
 */
public class ObserverDemo {
    public static void main(String[] args) {
        MyObservable observable = new MyObservable();
        observable.setChanged();
        //添加订阅者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                System.out.println(value);
            }
        });

        observable.notifyObservers("Hello,World");
    }

    public static class MyObservable extends Observable{
        protected void setChanged() {
            super.setChanged();
        }

    }
}
