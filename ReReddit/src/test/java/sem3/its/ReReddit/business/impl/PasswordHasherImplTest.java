package sem3.its.ReReddit.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.its.ReReddit.business.security.PasswordHasher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

 class PasswordHasherImplTest {

    @Test
    void hashString_ShouldReturnArgon2FormatBase64String(){
        PasswordHasherImpl passwordHasher = new PasswordHasherImpl();
        String password = "12345";

        String hash = passwordHasher.hash(password);

        assertEquals("$argon2i$v=19$m=65554,t=10,p=1$", hash.substring(0,31));
    }
}
