package src.com.ai.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PersistenciaPautas extends Persistencia {

	public static PersistenciaPautas instance;
	
	private PersistenciaPautas() {
	
	}
	
	public static PersistenciaPautas getInstance() {
		
		if( instance == null ) {
			instance = new PersistenciaPautas();
		}
		
		return instance;
	}
	

	public int selectTablaExcesos(int exceso) {
		try {
			int descuento;
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("select * from AplicacionesInteractivas.dbo.PautaTablaExcesos orderby exceso"); 
			/*revisar query que la hice asi nomas*/
			ResultSet result = statement.executeQuery();
			result.next();
			while(exceso<result.getInt("exceso")) {
			result.next();
			descuento = result.getInt("descuento");
			}
			return descuento;
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());	
			return null;
		}
	}

}

