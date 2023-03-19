import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LoginUserTest extends BaseApi {
    UserApi userApi = new UserApi();
    AssertUser userAssert = new AssertUser();

    @Before
    public void setUp() {
        openUri();
        userApi.registration(GeneratorUser.getCreateUser());
    }

    @Test
    @DisplayName("Проверка возможности успешного логина")
    public void successLoginUser() {
        Response status = userApi.loginUser(GeneratorUser.getUser());
        userAssert.statusOk(status);
    }

    @Test
    @DisplayName("Проверка возможности логина c неправильным Email")
    public void loginUserWithBadEmail() {
        Response status = userApi.loginUser(GeneratorUser.getUserBadEmail());
        userAssert.statusUnauthorized(status);
    }

    @Test
    @DisplayName("Проверка возможности логина c неправильным паролем")
    public void loginUserWithBadPassword() {
        Response status = userApi.loginUser(GeneratorUser.getUserBadPassword());
        userAssert.statusUnauthorized(status);
    }
    @After
    public void deleteUser() {
        userApi.deleteUser();
    }
}
