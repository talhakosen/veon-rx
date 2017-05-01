import io.reactivex.*;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by tctkosen on 4/29/17.
 */
public class Main implements MainView, MainRepository {
    static Scanner in;

    public static void main(String[] args) {
        Main main = new Main();

        MainPresenter mainPresenter = new MainPresenter(main, main);
        mainPresenter.bind();

        in = new Scanner(System.in);
        if (in != null && in.nextLine().equalsIgnoreCase("exit")) ;
        return;
    }


    @Override
    public Observable<String> getInterval() {
        return Observable.interval(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long along) throws Exception {
                        return along.toString();
                    }
                })
                .delay(200, TimeUnit.MILLISECONDS, Schedulers.trampoline());
    }

    @Override
    public void writeToScreenListener1(String text) {
        System.out.println("Listener1 : " + text);
    }

    @Override
    public void writeToScreenListener2(String text) {
        System.out.println("Listener2 : " + text);
    }

    @Override
    public void writeToScreenListener3(String text) {
        System.out.println("Listener3 : " + text);
    }
}
