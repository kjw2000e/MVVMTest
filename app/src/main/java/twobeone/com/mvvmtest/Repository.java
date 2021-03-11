package twobeone.com.mvvmtest;

public class Repository {
    private static final Repository repository = new Repository();

    public static Repository getInstance() {
        return repository;
    }

    private Repository() {
    }
}
