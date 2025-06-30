<img src="https://github.com/user-attachments/assets/6eb2f6eb-7a9c-4772-8028-54048472aa4a" width="20%" />

# KotSpam

**KotSpam** is a Kotlin-based tool designed to simulate automated message typing using the Java `Robot` class. It's great for experimenting with desktop automation or for educational purposes.

## 📦 Download

👉 [**Download KotSpam.jar from the latest release**](https://github.com/laetuz/KotSpam/releases)

No need to build — just download and run:

```bash
java -jar KotSpam.jar
```

## ⚙️ Features

- Simulate typing messages character-by-character
- Send messages via clipboard paste
- Custom delay control for realistic or fast input
- Command-line interface with multiple modes (quick send, spam loop, etc.)
- Cross-platform: macOS, Windows, Linux (Java required), Android (adb required)
- ADB Tools

## 🚀 Getting Started

### 🔧 Requirements

- Java 8+
- ADB (For ADB commands)
- Kotlin (for development)
- IntelliJ IDEA (optional)

### 📦 Build

To export to a runnable `.jar`:

```bash
kotlinc KotSpam.kt -include-runtime -d KotSpam.jar
```

Then run:
```bash
java -jar KotSpam.jar
```

