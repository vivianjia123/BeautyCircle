package utility;

public interface LockManager {

	public boolean acquireLock(Long lockable, String owner) throws Exception;

	public void releaseLock(Long lockable, String owner, long sessionId) throws Exception;

	public void releaseAllLocks(String owner) throws Exception;

	public boolean hasLock(Long lockable, String owner) throws Exception;

}
