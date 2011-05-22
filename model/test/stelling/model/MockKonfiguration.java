package stelling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class MockKonfiguration implements ISessionKonfiguration {
	private final List<OpgaveType> opgaveTyper;
	private final OpgaveType defaultOpgaveType;

	MockKonfiguration() {
		AttributType tilpasseligRammeType = lavMaterialeType(
				AttributBeregner.OMKREDS_PRIS, "Tilpasselig Ramme", "navn",
				"pris", "materiale", "farve", "id", "placering");
		AttributType faerdigRammeType = lavMaterialeType(
				AttributBeregner.OMKREDS_PRIS, "F¾rdigramme", "navn", "pris",
				"materiale", "farve", "id");
		AttributType glasType = lavMaterialeType(AttributBeregner.AREAL_PRIS,
				"Glas", "navn", "pris");
		AttributType bagpapType = lavMaterialeType(AttributBeregner.AREAL_PRIS,
				"Bagpap", "navn", "pris");
		AttributType baggrundType = lavMaterialeType(
				AttributBeregner.AREAL_PRIS, "Baggrund", "navn", "pris");
		OpgaveType tilpassetIndramning = lavOpgaveType("Tilpasset Indramning",
				tilpasseligRammeType, glasType, bagpapType, baggrundType);
		OpgaveType faerdigIndramning = lavOpgaveType("F¾rdigindramning",
				faerdigRammeType, baggrundType);
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

	private static AttributType lavMaterialeType(AttributBeregner beregner,
			String navn, String... feltNavne) {
		AttributType type = new ValgAttributType(navn,
				Arrays.asList(feltNavne), beregner);
		return type;
	}

	private static OpgaveType lavOpgaveType(String navn,
			AttributType... attributTyper) {
		OpgaveType type = new OpgaveType("Tilpasset Indramning",
				Arrays.asList(attributTyper));
		return type;
	}

	static OpgaveType lavOpgaveTypeMedDummeAttributter(String opgaveTypeNavn,
			String[] attributTypeNavne) {
		List<AttributType> attributTyper = new ArrayList<AttributType>();
		for (final String attributNavn : attributTypeNavne) {
			AttributType type = new ValgAttributType(attributNavn,
					Arrays.asList(new String[] {}), AttributBeregner.FAST_PRIS);
			attributTyper.add(type);
		}
		return new OpgaveType(opgaveTypeNavn, attributTyper);
	}
}
