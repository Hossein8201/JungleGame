
# Animals Jungle Game

Welcome to the **Animals Jungle Game**, a strategic card game designed for two players or one player against the computer. The game features animal-themed cards with unique attributes and gameplay mechanics. Players build their deck, strategize their attacks, and aim to defeat the opponent's cards.

---

## Table of Contents

1. [Game Description](#game-description)
2. [Features](#features)
3. [Installation](#installation)
4. [How to Play](#how-to-play)
5. [Classes Overview](#classes-overview)
6. [Project Structure](#project-structure)
7. [Author](#author)

---

## Game Description

The Animals Jungle Game is a turn-based card game where each card represents a unique animal with attributes such as:
- **Normal Hit**: Damage dealt in a standard attack.
- **Strong Hit**: Damage dealt in a power attack.
- **Energy**: Energy required to perform attacks.
- **Life**: Total health of the card.

Players take turns selecting cards to attack or recharge energy, and the game ends when one player's deck is completely defeated.

---

## Features

- Play against another player or the computer.
- Strategic card selection and energy management.
- Logs of each player's actions for transparency.
- Auto-generated card decks with customizable hands.
- Dynamic game flow with user and AI decision-making.

---

## Installation

1. Clone the repository or download the source code.
   ```bash
   git clone <repository-url>
   ```
2. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).
3. Compile the Java files using your IDE or the command line:
   ```bash
   javac *.java
   ```
4. Run the game:
   ```bash
   java GameSystem
   ```

---

## How to Play

1. **Start the Game**: Choose to play against another player or the computer.
2. **Build Your Deck**:
   - Each player selects 10 cards from their auto-generated deck of 30 cards.
   - Cards are chosen based on their attributes and strategic value.
3. **Game Process**:
   - Each player takes turns to attack the opponent's cards or recharge their own.
   - Choose cards for normal or strong attacks and select an opponent's card to target.
   - Energy recharges are limited to 3 attempts per player.
4. **Win Condition**:
   - A player wins when all opponent cards are defeated.

---

## Classes Overview

### 1. **Card**
   - Represents an animal card with attributes such as `name`, `normalHit`, `strongHit`, `energy`, `initialEnergy`, and `life`.

### 2. **PlayerHand**
   - Manages the player's hand of cards.
   - Handles deck initialization, card selection, and validation.

### 3. **Player**
   - Extends `PlayerHand` and represents a player (or the computer).
   - Handles player actions such as attacks and energy recharges.
   - Logs player activities for reference.

### 4. **GameSystem**
   - Acts as the main game controller and user interface.
   - Handles game flow, input, and player/computer actions.

---

## Project Structure

```
src/
├── Card.java          # Represents the animal cards.
├── PlayerHand.java    # Manages the player's card hand.
├── Player.java        # Represents a player or computer opponent.
├── GameSystem.java    # Main game logic and user interface.
```

---

Feel free to reach out for any questions or suggestions. Enjoy playing the Animals Jungle Game! 🐾
