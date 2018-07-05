package code.session.login;

public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();
}
