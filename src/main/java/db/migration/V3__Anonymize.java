package db.migration;

import com.real360.demo.features.users.User;
import com.real360.demo.features.users.userRepository.UserRepository;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.Statement;

public class V3__Anonymize extends BaseJavaMigration {
    @Autowired
    private UserRepository userRepository;
    User user = new User();
    public void migrate(Context context) throws Exception {
        this.userRepository.save(user);
        try (Statement select = context.getConnection().createStatement()) {
            try (ResultSet rows = select.executeQuery("SELECT id FROM users ORDER BY id")) {
                while (rows.next()) {
                    int id = rows.getInt(1);
                    String anonymizedName = "Anonymous" + id;
                    try (Statement update = context.getConnection().createStatement()) {
                        update.execute("UPDATE users SET name='" + anonymizedName + "' WHERE id=" + id);
                    }
                }
            }
        }
    }
}