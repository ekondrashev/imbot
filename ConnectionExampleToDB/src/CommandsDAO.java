import java.sql.SQLException;
import java.util.List;


public interface CommandsDAO {
	public void insert(Commands cmd) throws SQLException;
	public List<Commands> select() throws SQLException;
	public Boolean isCommandExist(String name) throws SQLException;
}
