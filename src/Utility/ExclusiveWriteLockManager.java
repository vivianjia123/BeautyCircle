package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExclusiveWriteLockManager implements LockManager {

	private static ExclusiveWriteLockManager instance;

	private static final String INSERT_SQL = "INSERT INTO lock values(?, ?)";
	private static final String DELETE_SINGLE_SQL = "DELETE FROM lock WHERE lockableid = ? and ownerid = ?";
	private static final String CHECK_SQL = "select lockableid from lock where lockableid = ? and ownerid = ?";
	private static final String DELETE_ALL_SQL = "delete from lock where ownerid = ?";

	public static ExclusiveWriteLockManager getInstance() {
		if (instance == null) {
			instance = new ExclusiveWriteLockManager();
		}

		return instance;
	}

	@Override
	public boolean acquireLock(Long lockable, String owner) throws Exception {
		boolean result = true;
		if (!hasLock(lockable, owner)) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {

				conn = DBConnection.getDbConnection();
				pstmt = conn.prepareStatement(INSERT_SQL);
				pstmt.setLong(1, lockable.longValue());
				pstmt.setString(2, owner);

				pstmt.executeUpdate();
				conn.commit();

			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
				return result;

			}
		}
		return result;

	}

	@Override
	public void releaseLock(Long lockable, String owner, long sessionId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getDbConnection();
			pstmt = conn.prepareStatement(DELETE_SINGLE_SQL);
			pstmt.setLong(1, lockable.longValue());
			pstmt.setString(2, owner);
			pstmt.setLong(3, sessionId);

			pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException sqlEx) {

			throw new Exception("unexpected error releasing lock on " + lockable);
		}
	}

	@Override
	public void releaseAllLocks(String owernID) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getDbConnection();
			pstmt = conn.prepareStatement(DELETE_ALL_SQL);
			pstmt.setString(1, owernID);

			pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException sqlEx) {

			throw new Exception("unexpected error releasing lock on " + owernID);
		}
	}

	@Override
	public boolean hasLock(Long lockable, String owner) throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = null;
		Boolean result = false;
		try {
			conn = DBConnection.getDbConnection();
			pstmt = conn.prepareStatement(CHECK_SQL);
			pstmt.setLong(1, lockable);
			pstmt.setString(2, owner);

			ResultSet resultSet = pstmt.executeQuery();
			DBConnection.commit();

			while (resultSet.next()) {
				long lock = resultSet.getLong(1);
				if (lock == lockable) {
					result = true;
				} else {
					result = false;
				}
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		return result;

	}

}
