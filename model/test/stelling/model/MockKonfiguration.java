package stelling.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class MockKonfiguration implements ISessionKonfiguration {
	private final List<OpgaveType> opgaveTyper;
	private final OpgaveType defaultOpgaveType;

	MockKonfiguration() {
		MaterialeType tilpasseligRammeType = new OmkredsPrisMaterialeType(
				"Tilpasselig Ramme");
		MaterialeType faerdigRammeType = new OmkredsPrisMaterialeType(
				"F¾rdigramme");
		MaterialeType glasType = new ArealPrisMaterialeType("Glas");
		MaterialeType bagpapType = new ArealPrisMaterialeType("Bagpap");
		MaterialeType baggrundType = new ArealPrisMaterialeType("Baggrund");
		OpgaveType tilpassetIndramning = new OpgaveType("Tilpasset Indramning",
				Arrays.asList(new IOpgaveAttributType[] { tilpasseligRammeType,
						glasType, bagpapType, baggrundType }));
		OpgaveType faerdigIndramning = new OpgaveType("F¾rdigindramning",
				Arrays.asList(new IOpgaveAttributType[] { faerdigRammeType,
						baggrundType }));
		opgaveTyper = Arrays.asList(new OpgaveType[] { tilpassetIndramning,
				faerdigIndramning });
		defaultOpgaveType = tilpassetIndramning;
	}

	@Override
	public Collection<OpgaveType> opgaveTyper() {
		return opgaveTyper;
	}

	@Override
	public OpgaveType defaultOpgaveType() {
		return defaultOpgaveType;
	}

	private MaterialeType lavMaterialeType(String navn, String[] feltNavne) {
		// TODO: Implement
		return null;
	}
}
