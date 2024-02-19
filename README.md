# Assignment_1---The-First-Android-App

## JourneyApp

### Description
The `JourneyApp` is a simple Android application designed to simulate a journey through multiple stops along a route. It allows users to track their progress, switch between routes, and switch between units of distance measurement (kilometers and miles). 

### Usage
To use the `JourneyApp`, follow these steps:
1. Launch the application on your Android device.
2. The main screen displays information about the journey, including the current stop, distance to the next stop, total distance covered, total distance left, and a progress indicator.
3. If the journey is not complete and there are remaining stops, you can:
   - Switch between kilometers and miles by tapping the "Switch units" button.
   - Proceed to the next stop by tapping the "Next stop" button.
   - Switch between routes by tapping the "Switch route" button.
4. If the journey is complete, a summary card will be displayed, showing the total distance covered and a message indicating that the journey is complete.
5. To restart the journey, tap the "Restart journey" button.

### Logic and Functions
- **MainActivity**: Entry point of the application. Sets up the theme and UI components.
- **JourneyApp**: Composable function responsible for rendering the main UI of the application. It manages state variables such as `distanceInKm`, `currentStop`, `useRoute2`, and `isComplete`. It calculates and displays journey-related information like current stop, distance to next stop, total distance covered, total distance left, and progress. It also provides buttons for unit switching, proceeding to the next stop, switching routes, and restarting the journey.
- **StopCard**: Composable function to render individual stop cards displaying stop name and distance. Used within the `JourneyApp` to display each stop along the route.
- **JourneyAppPreview**: Composable function for previewing the `JourneyApp` UI in Android Studio's layout editor.
Certainly! Let's delve into the detailed logic of the code implementation:

## Distance Conversion Logic
- **State Variable**: 
  - `distanceInKm`: This boolean variable determines whether the distances are displayed in kilometers (`true`) or miles (`false`).

- **Conversion Formula**:
  - When `distanceInKm` is `true`, distances are displayed in kilometers directly.
  - When `distanceInKm` is `false`, distances are converted to miles using the formula: `distance_in_miles = distance_in_km * 0.621371`.

- **UI Display**:
  - The text displays the current distance to the next stop in either kilometers or miles based on the value of `distanceInKm`.
  - A button labeled "Switch units" toggles the `distanceInKm` variable, allowing the user to switch between kilometers and miles.

## Next Stop and Route Progress Logic
- **State Variables**:
  - `currentStop`: This variable keeps track of the index of the current stop in the route.
  - `useRoute2`: This boolean variable determines whether the application uses `route1` or `route2`.
  - `isComplete`: This boolean variable indicates whether the journey is complete or not.

- **Next Stop Button**:
  - The "Next stop" button increments the `currentStop` variable, allowing the user to proceed to the next stop in the route.
  - If the `currentStop` is equal to the last stop index in the route, the `isComplete` variable is set to `true`, indicating that the journey is complete.

## Route Switching Logic
- **State Variable**:
  - `useRoute2`: This boolean variable determines whether to use `route1` or `route2`.

- **Switch Route Button**:
  - The "Switch route" button toggles the `useRoute2` variable, allowing the user to switch between routes.

## UI Rendering Logic
- **Composable Functions**:
  - `JourneyApp`: Renders the main UI of the application.
  - `StopCard`: Renders individual stop cards displaying stop name and distance.

- **Conditional Rendering**:
  - The UI elements are conditionally rendered based on the state variables and application logic.
  - For example, if the journey is complete, a summary card is displayed; otherwise, the journey progress and stop information are shown.

## Restart Journey Logic
- **Restart Button**:
  - The "Restart journey" button resets the `currentStop` variable to `0` and sets `isComplete` to `false`, allowing the user to restart the journey from the beginning.

### Dependencies
- `androidx.activity.ComponentActivity`
- `androidx.activity.compose.setContent`
- `androidx.compose.foundation.*`
- `androidx.compose.material3.*`
- `androidx.compose.runtime.*`
- `androidx.compose.ui.*`
- `androidx.compose.ui.tooling.preview.Preview`
- `com.psuedo.journeyapp.ui.theme.JourneyAppTheme`
