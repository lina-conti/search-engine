package interrogation;

import java.util.HashMap;

import indexation.Index;

public abstract class Vectorial extends IRModel {
	protected Weighter w;

	public Vectorial(Index index, Weighter w) {
		super(index);
		this.w = w;
	}

	@Override
	public abstract HashMap<String, Double> getDocScores(HashMap<String, Integer> queryProcessed);

}
