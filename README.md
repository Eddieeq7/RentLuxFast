# RentLuxFast

Java OOP class project demonstrating all 5 OOP principles with a JavaFX desktop UI.

## Requirements

- Java 21+
- Maven — `brew install maven`

## Run

```bash
git clone https://github.com/Eddieeq7/RentLuxFast.git
cd RentLuxFast
mvn javafx:run
```

That's it. Maven handles all dependencies automatically.

## OOP Principles

| Principle | Where |
|---|---|
| Encapsulation | Private fields + getters/setters in `LuxuryVehicle`, `Member`, `Booking` |
| Inheritance | `Supercar`, `LuxurySUV`, `Exotic` extend `LuxuryVehicle`; `GoldMember`, `PlatinumMember` extend `Member` |
| Polymorphism | `calculateRentalCost()` and `getDiscount()` resolve to the correct subclass at runtime |
| Abstraction | `LuxuryVehicle` and `Member` are abstract classes |
| Interfaces | `Rentable` interface implemented by `LuxuryVehicle` |
