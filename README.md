### Group Members: Luca Ornstil, Monish Suresh, Christine Widden
----
### Report: Instability and Cyclomatic Complexity Calculations
---
### Main.java

#### Instability Calculation:

- The `Main` class depends on the following external classes:
  - `Blackboard`
  - `WorkArea`
  - `Server`
  - `Thread`
  
  Therefore, **out = 4**.

- No other classes depend on the `Main` class, so:
  - **in = 0**
  
  **Instability of Main.java**: 1.0

#### Cyclomatic Complexity Counts:

- `Main()` constructor:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `main(String[] args)`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `actionPerformed(ActionEvent e)`:
  - Contains 3 `if-else` conditions to check which button was pressed (`startMenuItem`, `stopMenuItem`, `configureMenuItem`).
  - **Cyclomatic Complexity = 4** (3 conditions + 1)

- `startTracking()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `stopTracking()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `configureSettings()`:
  - No conditional logic or loops, just sequential logic for setting size and speed.
  - **Cyclomatic Complexity = 1**

---

### **Blackboard.java**

#### Instability Calculation:

- The `Blackboard` class does not depend on any external classes.
  - **out = 0**

- The `Blackboard` class is depended on by the following classes:
  - `Main`
  - `Server`
  - `WorkArea`

  Therefore, **in = 3**.

  **Instability of Blackboard.java**: 0.0

#### Cyclomatic Complexity Counts:

- `Blackboard()` constructor:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `addClick(Point click)`:
  - Contains 1 `if` condition to check if tracking is active.
  - **Cyclomatic Complexity = 2** (1 condition + 1)

- `getClickPositions()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `clearClicks()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `getTransmissionSpeed()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `setTransmissionSpeed(int speed)`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `isTracking()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `startTracking()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `stopTracking()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

---

### **Server.java**

#### Instability Calculation:

- The `Server` class depends on the following external classes:
  - `Blackboard`
  - `Timer`
  - `Socket`
  - `ObjectOutputStream`
  
  Therefore, **out = 4**.

- The `Server` class is depended on by the `Main` class.
  - **in = 1**

  **Instability of Server.java**: 0.8

#### Cyclomatic Complexity Counts:

- `Server(Blackboard blackboard)` constructor:
  - Contains 1 `try-catch` block, which counts as a decision point.
  - **Cyclomatic Complexity = 2** (1 condition + 1)

- `run()`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `startTransmission()`:
  - Contains 1 `if` condition to check if data is already being transmitted and 1 condition inside the timer task.
  - **Cyclomatic Complexity = 3** (2 conditions + 1)

- `stopTransmission()`:
  - Contains 1 `if` condition to check if data is being transmitted and whether the timer is active.
  - **Cyclomatic Complexity = 2** (1 condition + 1)

- `sendClickData()`:
  - Contains 1 `if` condition to check if the click list is empty and 1 `try-catch` block.
  - **Cyclomatic Complexity = 3** (2 conditions + 1)

- `stopServer()`:
  - Contains 1 `try-catch` block and 1 `if` condition to close the socket.
  - **Cyclomatic Complexity = 2** (1 condition + 1)

---

### **WorkArea.java**

#### Instability Calculation:

- The `WorkArea` class depends on the following external classes:
  - `Blackboard`
  - `MouseListener`
  
  Therefore, **out = 2**.

- The `WorkArea` class is depended on by the `Main` class.
  - **in = 1**

  **Instability of WorkArea.java**: 0.67

#### Cyclomatic Complexity Counts:

- `WorkArea(Blackboard blackboard)` constructor:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `mouseReleased(MouseEvent e)`:
  - Contains 1 `if` condition to check if tracking is active.
  - **Cyclomatic Complexity = 2** (1 condition + 1)

- `mouseClicked(MouseEvent e)`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `mousePressed(MouseEvent e)`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `mouseEntered(MouseEvent e)`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**

- `mouseExited(MouseEvent e)`:
  - No conditional logic or loops.
  - **Cyclomatic Complexity = 1**
