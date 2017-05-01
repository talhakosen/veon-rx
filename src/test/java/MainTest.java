import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tctkosen on 5/1/17.
 */
public class MainTest {

    @Mock
    private MainRepository mainRepository;

    @Mock
    private MainView mainView;

    private MainPresenter presenter;

    @BeforeClass
    public static void setUpClass() {
        RxJavaPlugins.setInitIoSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(mainRepository, mainView);
    }

    @AfterClass
    public static void tearDownClass() {
        RxJavaPlugins.reset();
    }

    @Test
    public void bind_WriteScreen_whenGetIntervalEmits() throws Exception {
        when(mainRepository.getInterval()).thenReturn(Observable.just("1"));

        presenter.bind();

        verify(mainView).writeToScreenListener1("1");
        verify(mainView).writeToScreenListener2("1");
        verify(mainView).writeToScreenListener3("1");
    }
}