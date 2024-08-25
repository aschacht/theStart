package theStart.theStuff;

public enum FlatLanderType {
	InputNuron, PyramidalNuron, MultipolarNuron, BipolarNuron, PurkinjeNuron;

	
	
	public static FlatLanderType ofType(int inputType) {
		switch(inputType) {
		case 0:
			return FlatLanderType.InputNuron;
		case 1:
			return FlatLanderType.PyramidalNuron;
		case 2:
			return FlatLanderType.MultipolarNuron;
		case 3:
			return FlatLanderType.BipolarNuron;
		case 4:
			return FlatLanderType.PurkinjeNuron;
		default:
			return FlatLanderType.InputNuron;
		
		}
		
	}
	
	
}

