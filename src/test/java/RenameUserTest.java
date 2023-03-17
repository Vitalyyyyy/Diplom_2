import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class RenameUserTest extends BaseApi {
    UserApi userApi = new UserApi();
    AssertUser userAssert = new AssertUser();

    @Before
    public void setUp() {
        openUri();
        userApi.registration(GeneratorUser.getCreateUser());
    }

    @Test
    @DisplayName("Проверка возможности изменения Email авторизованного пользователя")
    public void renameEmailWithAuthorization() {
        Response status = userApi.rename("email", GeneratorUser.getNewEmail(), userApi.getToken());
        userAssert.statusOk(status);
    }

    @Test
    @DisplayName("Проверка возможности изменения имени авторизованного пользователя")
    public void renameNameWithAuthorization() {
        Response status = userApi.rename("name", GeneratorUser.getNewName(), userApi.getToken());
        userAssert.statusOk(status);
    }

    @Test
    @DisplayName("Проверка возможности изменения Email неавторизованного пользователя")
    public void renameEmailNonAuthorization() {
        Response status = userApi.rename("email", GeneratorUser.getNewEmail(), "");
        userAssert.statusUnauthorized(status);
    }

    @Test
    @DisplayName("Проверка возможности изменения имени неавторизованного пользователя")
    public void renameNameNonAuthorization() {
        Response status = userApi.rename("name", GeneratorUser.getNewName(), "");
        userAssert.statusUnauthorized(status);
    }

    @After
    public void deleteUser() {
        userApi.deleteUser();
    }
}