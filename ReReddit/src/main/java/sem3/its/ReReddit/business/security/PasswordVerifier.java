package sem3.its.ReReddit.business.security;

public interface PasswordVerifier {
    boolean verify(String hash, char[] plainText);
}
