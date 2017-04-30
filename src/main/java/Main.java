import io.reactivex.*;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by tctkosen on 4/29/17.
 */
public class Main {
    static Scanner in;

    public static void main(String[] args) {

        ConnectableObservable<String> connObservable = getObservable().publish();

        connObservable.subscribe(getObserver("Observer1"));

        connObservable.subscribe(getObserver("Observer2"));

        connObservable.subscribe(getObserver("Observer3"));

        connObservable.connect();

        in = new Scanner(System.in);
        if (in != null && in.nextLine().equalsIgnoreCase("exit")) ;
        return;
    }

    private static Observable<String> getObservable() {
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

    private static Observer<String> getObserver(final String identifier) {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(identifier + " " + s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        };
    }
}
