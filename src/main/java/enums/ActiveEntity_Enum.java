package enums;



public enum ActiveEntity_Enum {
	Aereo,	// 0
	Aeroporto,	// 1
	Volo,	// 2
	Biglietto,	// 3
	Cliente		// 4
}

// Scrivere ActiveEntity_Enum è come scrivere un tipo dato 
// ActiveEntity_Enum ATTRIBUTO

// Io voglio assegnare ad ATTRIBUTO il valore dell'enum (il valore 3)
// Io posso fare ATTRIBUTO = 3; (NON HA SENSO FARLO)
// Io invece posso fare ATTRIBUTO = ActiveEntity_Enum.Biglietto; (io dentro attributo ho il valore 3)