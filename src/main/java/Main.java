import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

/**
 * Created by tctkosen on 4/29/17.
 */
public class Main {
    public static void main(String[] args) {
        Observable<String> observable = Observable.interval(100, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.computation())
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long along) throws Exception {
                        return along.toString();
                    }
                }).delay(200, TimeUnit.MILLISECONDS, Schedulers.trampoline());

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.print("o1 " + s);
            }
        });

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.print("o2 " + s);
            }
        });

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.print("o3 " + s);
            }
        });
    }

}
