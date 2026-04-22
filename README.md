# RentLuxFast 🚗

A luxury vehicle rental system built in Java, demonstrating all 5 core OOP principles:
**Encapsulation · Inheritance · Polymorphism · Abstraction · Interfaces**

Built as a Java OOP class project with a JavaFX desktop UI.

---

## Requirements

- **Java 21+** — [Download Temurin](https://adoptium.net/)
- **Maven 3.8+** — Install via Homebrew:
  ```bash
  brew install maven
  ```

> Both teammates are on macOS (Apple Silicon). JavaFX 21 is bundled via Maven — no manual JavaFX install needed.

---

## Setup & Run

```bash
# 1. Clone the repo
git clone https://github.com/Eddieeq7/RentLuxFast.git
cd RentLuxFast

# 2. Run (Maven downloads all dependencies automatically)
mvn javafx:run
```

A desktop window will open — no browser or localhost needed.

---

## Project Structure

```
src/main/java/
├── app/                  # Entry point (MainApp.java)
├── ui/
│   ├── NavigationManager.java   # Screen routing
│   ├── views/                   # 5 screens
│   │   ├── FleetBrowserView     # Home — browse vehicles
│   │   ├── VehicleDetailView    # Detail + booking form
│   │   ├── LoginView            # Auth gate before booking
│   │   ├── TrackerView          # Delivery status
│   │   └── ReceiptView          # Final receipt
│   └── components/
│       └── VehicleCard          # Reusable card component
├── vehicles/             # LuxuryVehicle (abstract), Supercar, LuxurySUV, Exotic
├── members/              # Member (abstract), GoldMember, PlatinumMember
├── booking/              # Booking (availability + pricing logic)
└── interfaces/           # Rentable (interface)

src/main/resources/
└── styles/luxury.css     # Luxury UI theme
```

---

## OOP Principles Demonstrated

| Principle | Where |
|---|---|
| **Encapsulation** | Private fields + getters/setters in `LuxuryVehicle`, `Member`, `Booking` |
| **Inheritance** | `Supercar`, `LuxurySUV`, `Exotic` extend `LuxuryVehicle` |
| **Polymorphism** | `calculateRentalCost()`, `getDiscount()`, `canAccessExclusive()` overridden per class |
| **Abstraction** | `LuxuryVehicle` and `Member` are abstract classes |
| **Interfaces** | `Rentable` interface implemented by `LuxuryVehicle` |

---

## App Flow

```
Fleet Browser → View Details → Book Now → Login (if not logged in) → Tracker → Receipt
```

- Browse and filter vehicles without logging in
- Login is only required when booking
- Use Member ID `M001` (Gold) or `M002` (Platinum) on the login screen
