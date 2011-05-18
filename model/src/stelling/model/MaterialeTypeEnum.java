package stelling.model;

/**
 * Enumeration over de materialetyper systemet underst¿tter. Brug denne
 * enumeration til at instantiere nye materialetyper
 */
public enum MaterialeTypeEnum {
	AREAL_PRIS {
		@Override
		public MaterialeType nyMaterialeType(String navn) {
			return new ArealPrisMaterialeType(navn);
		}
	},
	OMKREDS_PRIS {
		@Override
		public MaterialeType nyMaterialeType(String navn) {
			return new OmkredsPrisMaterialeType(navn);
		}
	},
	FAST_PRIS {
		@Override
		public MaterialeType nyMaterialeType(String navn) {
			return new FastPrisMaterialeType(navn);
		}
	};

	public abstract MaterialeType nyMaterialeType(String navn);
}
