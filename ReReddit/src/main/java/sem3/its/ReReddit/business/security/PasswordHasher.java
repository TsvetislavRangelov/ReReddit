package sem3.its.ReReddit.business.security;


public interface PasswordHasher {
    String hash(String plainText);
}
