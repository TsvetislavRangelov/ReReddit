package sem3.its.ReReddit.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PasswordHasherImpl implements PasswordHasher {
    @Override
    public String hash(String plainText){
        Argon2 argon2 = Argon2Factory.create();
        char[] password = plainText.toCharArray();
        try{

            return argon2.hash(10, 65554, 1, password);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            argon2.wipeArray(password);
        }
        return null;
    }
}
