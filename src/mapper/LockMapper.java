package mapper;

import domain.DomainObject;
import utility.ExclusiveWriteLockManager;
import utility.LockManager;

public class LockMapper extends DataMapper {

	private DataMapper impl;
	private LockManager lm;
	private String owner;

	public LockMapper(DataMapper impl, String owner) {
		this.impl = impl;
		this.lm = ExclusiveWriteLockManager.getInstance();
		this.owner = owner;
	}

	@Override
	public void insert(DomainObject object) {
		impl.insert(object);
	}

	@Override
	public void update(DomainObject object) {
		try {
			if (lm.hasLock(object.getId(), owner)) {
				impl.update(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(DomainObject object) {
		try {
			if (lm.hasLock(object.getId(), owner)) {
				impl.delete(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
