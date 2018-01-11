package form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * userBody for user register
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBody {
    String name;
    String email;
    String password;
}
