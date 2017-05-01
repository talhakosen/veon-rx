import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter {
    private final MainRepository mainRepository;
    private final MainView mainView;

    private final CompositeDisposable disposable = new CompositeDisposable();


    public MainPresenter(MainRepository mainRepository, MainView mainView) {
        this.mainRepository = mainRepository;
        this.mainView = mainView;
    }

    void bind() {
        disposable.add(mainRepository.getInterval()
                .subscribe(mainView::writeToScreenListener1));

        disposable.add(mainRepository.getInterval()
                .subscribe(mainView::writeToScreenListener2));

        disposable.add(mainRepository.getInterval()
                .subscribe(mainView::writeToScreenListener3));
    }

    void unbind() {
        disposable.clear();
    }

}
