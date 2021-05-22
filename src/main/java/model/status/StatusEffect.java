package model.status;

import model.Entity;

public interface StatusEffect <T extends Entity>{
	
	
	public void addBonus(T entity, long duration);

	
}
