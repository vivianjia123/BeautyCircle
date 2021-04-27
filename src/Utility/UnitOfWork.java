package utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.DomainObject;
import mapper.DataMapper;

public class UnitOfWork {
	@SuppressWarnings("rawtypes")
	private static ThreadLocal current = new ThreadLocal();

	private List<DomainObject> newObjects = new ArrayList<DomainObject>();
	private List<DomainObject> dirtyObjects = new ArrayList<DomainObject>();
	private List<DomainObject> deletedObjects = new ArrayList<DomainObject>();

	public static void newCurrent() {
		setCurrent(new UnitOfWork());
	}

	@SuppressWarnings("unchecked")
	public static void setCurrent(UnitOfWork unitOfWork) {
		current.set(unitOfWork);
	}

	public static UnitOfWork getCurrent() {
		return (UnitOfWork) current.get();
	}

	public void registerNew(DomainObject domainObject) {
		if (!checkList(domainObject)) {
			newObjects.add(domainObject);
		}
	}

	public void registerDirty(DomainObject domainObject) {
		if (!dirtyObjects.contains(domainObject) && !newObjects.contains(domainObject)) {
			dirtyObjects.add(domainObject);
		}
	}

	public void registerDeleted(DomainObject domainObject) {
		if (newObjects.remove(domainObject)) {
			return;
		}
		dirtyObjects.remove(domainObject);
		if (!checkList(domainObject)) {
			deletedObjects.add(domainObject);
		}
	}

	public boolean checkList(DomainObject domainObject) {
		if (dirtyObjects.contains(domainObject)) {
			return true;
		}
		if (deletedObjects.contains(domainObject)) {
			return true;
		}
		if (newObjects.contains(domainObject)) {
			return true;
		}
		return false;
	}

	public void commit() {
		insertNew();
		updateDirty();
		deleteRemoved();
	}

	private void insertNew() {
		for (Iterator<DomainObject> objects = newObjects.iterator(); objects.hasNext();) {
			DomainObject domainObject = objects.next();
			DataMapper.getMapper(domainObject.getClass()).insert(domainObject);
		}
	}

	private void updateDirty() {
		for (Iterator<DomainObject> objects = dirtyObjects.iterator(); objects.hasNext();) {
			DomainObject domainObject = objects.next();
			DataMapper.getMapper(domainObject.getClass()).update(domainObject);
		}
	}

	private void deleteRemoved() {
		for (Iterator<DomainObject> objects = deletedObjects.iterator(); objects.hasNext();) {
			DomainObject domainObject = objects.next();
			DataMapper.getMapper(domainObject.getClass()).delete(domainObject);
		}
	}

}
