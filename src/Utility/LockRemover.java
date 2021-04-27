package utility;

import javax.servlet.http.HttpSessionBindingListener;

public class LockRemover implements HttpSessionBindingListener {

	private String sessionId;

	public LockRemover(String sessionId) {
		this.sessionId = sessionId;
	}

	public void valueUnbound() throws Exception {
		ExclusiveWriteLockManager.getInstance().releaseAllLocks(this.sessionId);
	}

}
