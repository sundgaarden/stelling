package stelling.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class MockKonfiguration implements ISessionKonfiguration {
	private final List<OpgaveType> opgaveTyper;
	private final OpgaveType defaultOpgaveType;

	MockKonfiguration() {
		MaterialeType tilpasseligRammeType = lavMaterialeType(
				MaterialeTypeEnum.OMKREDS_PRIS, "Tilpasselig Ramme",
				new String[] { "navn", "pris", "materiale", "farve", "id",
						"placering" });
		MaterialeType faerdigRammeType = lavMaterialeType(
				MaterialeTypeEnum.OMKREDS_PRIS, "F¾rdigramme", new String[] {
						"navn", "pris", "materiale", "farve", "id" });
		MaterialeType glasType = lavMaterialeType(MaterialeTypeEnum.AREAL_PRIS,
				"Glas", new String[] { "navn", "pris" });
		MaterialeType bagpapType = lavMaterialeType(
				MaterialeTypeEnum.AREAL_PRIS, "Bagpap", new String[] { "navn",
						"pris" });
		MaterialeType baggrundType = lavMaterialeType(
				MaterialeTypeEnum.AREAL_PRIS, "Baggrund", new String[] {
						"navn", "pris" });
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

	private MaterialeType lavMaterialeType(MaterialeTypeEnum materialeTypeType,
			String navn, String[] feltNavne) {
		MaterialeType type = materialeTypeType.nyMaterialeType(navn);
		type.setFeltNavne(Arrays.asList(feltNavne));
		return type;
	}
}
