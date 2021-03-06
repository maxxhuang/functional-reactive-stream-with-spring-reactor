package tenam.learning.reactivespringreactor.devicesimulator.generator;

import com.google.common.base.Preconditions;

public class StepGenerator<V> extends AbstractValueGenerator<V> {

	private int step;
	private int currentStep;
	private boolean currentValueLoaded;
	private V currentValue;
	private ValueGenerator<V> generator;
	
	public StepGenerator(int step, ValueGenerator<V> generator) {
		checkStep(step);
		Preconditions.checkNotNull(generator, "generator must not be null");
		this.step = step;
		this.currentStep = 0;
		this.currentValueLoaded = false;
		this.currentValue = null;
		this.generator = generator;
	}
	
	@Override
	public V generate() {
		if (this.currentStep >= this.step || !this.currentValueLoaded) {
			stepBack();
		} else {
			stepOne();
		}
		return this.currentValue;
	}
	
	public int getStep() {
	    return this.step;
	}
	
	private void stepBack() {
		this.currentStep = 1;
		this.currentValue = this.generator.generate();
		this.currentValueLoaded = true;
	}
	
	private void stepOne() {
		this.currentStep++;
	}

	private static void checkStep(int step) {
		if (step < 1) {
			throw new IllegalArgumentException("step must be greater than 0");
		}
	}

}
