package domain;

import utility.UnitOfWork;

public class DomainObject {

	public DomainObject() {

	}

	public long getId() {
		return 0;
	}

	public String getCardNumber() {
		return null;
	}

	protected void markNew(DomainObject object) {
		UnitOfWork.getCurrent().registerNew(object);
	}

	protected void markDirty(DomainObject object) {
		UnitOfWork.getCurrent().registerDirty(object);
	}

	protected void markDeleted(DomainObject object) {
		UnitOfWork.getCurrent().registerDeleted(object);
	}
}
