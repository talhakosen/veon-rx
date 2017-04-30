import io.reactivex.*;
import io.reactivex.disposables.Disposable;
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
        Observable.interval(100, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.computation());
    }
}
