import io.reactivex.Observable;

interface MainRepository {
    Observable<String> getInterval();
}
