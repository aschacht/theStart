package theStart.theStuff;

public class Synapse {

	private SynapseType synapseType;

	public Synapse(SynapseType synapseType) {
		this.setSynapseType(synapseType);
	}

	public SynapseType getSynapseType() {
		return synapseType;
	}

	public void setSynapseType(SynapseType synapseType) {
		this.synapseType = synapseType;
	}

}
