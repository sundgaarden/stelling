package stelling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class MockKonfiguration implements ISessionKonfiguration {
	private final List<OpgaveType> opgaveTyper;
	private final OpgaveType defaultOpgaveType;

	MockKonfiguration() {
		MaterialeType tilpasseligRammeType = lavMaterialeType(
				MaterialeTypeEnum.OMKREDS_PRIS, "Tilpasselig Ramme", "navn",
				"pris", "materiale", "farve", "id", "placering");
		MaterialeType faerdigRammeType = lavMaterialeType(
				MaterialeTypeEnum.OMKREDS_PRIS, "F¾rdigramme", "navn", "pris",
				"materiale", "farve", "id");
		MaterialeType glasType = lavMaterialeType(MaterialeTypeEnum.AREAL_PRIS,
				"Glas", "navn", "pris");
		MaterialeType bagpapType = lavMaterialeType(
				MaterialeTypeEnum.AREAL_PRIS, "Bagpap", "navn", "pris");
		MaterialeType baggrundType = lavMaterialeType(
				MaterialeTypeEnum.AREAL_PRIS, "Baggrund", "navn", "pris");
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

	private static MaterialeType lavMaterialeType(
			MaterialeTypeEnum materialeTypeType, String navn,
			String... feltNavne) {
		MaterialeType type = materialeTypeType.nyMaterialeType(navn);
		type.setFeltNavne(Arrays.asList(feltNavne));
		return type;
	}

	private static OpgaveType lavOpgaveType(String navn,
			IOpgaveAttributType... attributTyper) {
		OpgaveType type = new OpgaveType("Tilpasset Indramning",
				Arrays.asList(attributTyper));
		return type;
	}

	static OpgaveType lavOpgaveTypeMedDummeAttributter(String opgaveTypeNavn,
			String[] attributTypeNavne) {
		List<IOpgaveAttributType> attributTyper = new ArrayList<IOpgaveAttributType>();
		for (final String attributNavn : attributTypeNavne) {
			attributTyper.add(new DumAttributType(attributNavn));
		}
		return new OpgaveType(opgaveTypeNavn, attributTyper);
	}

	private static class DumAttributType implements IOpgaveAttributType {
		private final IOpgaveAttribut defaultAttribut;
		private final String navn;

		private DumAttributType(String inNavn) {
			defaultAttribut = new DumAttribut(this);
			navn = inNavn;
		}

		@Override
		public String navn() {
			return navn;
		}

		@Override
		public List<String> feltNavne() {
			return Collections.emptyList();
		}

		@Override
		public IOpgaveAttribut defaultVaerdi() {
			return defaultAttribut;
		}
	}

	private static class DumAttribut implements IOpgaveAttribut {
		private final IOpgaveAttributType type;

		private DumAttribut(IOpgaveAttributType InType) {
			type = InType;
		}

		@Override
		public Beloeb pris(LaengdeMaal hoejde, LaengdeMaal bredde) {
			return Beloeb.NUL;
		}

		@Override
		public IOpgaveAttributType attributType() {
			return type;
		}

		@Override
		public String navn() {
			return type.navn() + "<singleton>";
		}

		@Override
		public String feltVaerdi(String feltNavn) {
			return "";
		}
	}
}
