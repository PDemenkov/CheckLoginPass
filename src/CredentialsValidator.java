import java.util.Objects;

public class CredentialsValidator {
    private static final String INVALID_LENGTH = "Длина может быть от 1 до 20";
    private static final String INVALID_SYMBOl = "Строка должна содержать только латинские буквы,цифры, или знак подчеркивания";
    public static final String PASSWORD_NOT_MATCH = "Пароль и подтверждение пароля не совпадают";
    public static boolean validateCredentials(String login,
                                              String password,
                                              String confirmPassword) {
        boolean loginValid = islengthInRange(login, 1, 20) && isSymbolValid(login);
        if (!loginValid) {
            throw new WrongLoginException(INVALID_LENGTH);
        }
        if (!isSymbolValid(login)) {
            throw new WrongLoginException(INVALID_SYMBOl);
        }
        if (!islengthInRange(password, 1, 20)) {
            throw new WrongPasswordException(INVALID_LENGTH);
        }
        if (!isSymbolValid(password)) {
            throw new WrongPasswordException(INVALID_SYMBOl);
        }
        if (!Objects.equals(password, confirmPassword)) {
            throw new WrongPasswordException(PASSWORD_NOT_MATCH);
        }
//        boolean passwordValid = islengthInRange(password, 1, 20)
//                && isSymbolValid(password)
//                && Objects.equals(password, confirmPassword);
//        if (!passwordValid) {
//            throw new WrongPasswordException("Неправильный пароль");
//        }
        return true;
    }

    private static boolean islengthInRange(String value, int min, int max) {
        if (value == null) {
            return false;
        }
        int length = value.length();
        return length >= min && length <= max;
    }

    private static boolean isSymbolValid(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (char c : value.toCharArray()) {
            boolean symbolMatches = (c >= 'a' && c < 'z')
                    || (c >= 'A' && c <= 'Z')
                    || (Character.isDigit(c))
                    || (c == '_');
            if (!symbolMatches) {
                return false;
            }
        }
        return true;
    }
}

