import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CreateUserTest extends BaseApi {
    UserApi userApi = new UserApi();
    AssertUser userAssert = new AssertUser();

    @Before
    public void setUp() {
        openUri();
    }

    @Test
    @DisplayName("Проверка возможности создать нового пользователя")
    public void successfulRegistrationNewUser() {
        Response status = userApi.registration(GeneratorUser.getCreateUser());
        userAssert.statusOk(status);
    }

    @Test
    @DisplayName("Попытка создать пользователя с повторяющимся именем")
    public void registrationDuplicateUser() {
        userApi.registration(GeneratorUser.getCreateUser());
        Response status = userApi.registrationWhithoutExtractToken(GeneratorUser.getCreateUser());
        userAssert.statusForbidden(status);
    }

    @Test
    @DisplayName("Попытка создания пользователя без заполненного email")
    public void registrationUserWithoutEmail() {
        Response status = userApi.registrationWhithoutExtractToken(GeneratorUser.getCreateUserWithoutEmail());
        userAssert.statusForbidden(status);
    }

    @Test
    @DisplayName("Попытка создания пользователя без заполненного Password")
    public void registrationUserWithoutPassword() {
        Response status = userApi.registrationWhithoutExtractToken(GeneratorUser.getCreateUserWithoutPassword());
        userAssert.statusForbidden(status);
    }

    @Test
    @DisplayName("Попытка создания пользователя без заполненного имени пользователя")
    public void registrationUserWithoutUserName() {
        Response status = userApi.registrationWhithoutExtractToken(GeneratorUser.getCreateUserWithoutUserName());
        userAssert.statusForbidden(status);
    }

    @After
    public void deleteUser() {
        userApi.deleteUser();
    }
}