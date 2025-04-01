document.addEventListener("DOMContentLoaded", function () {
    const themeButton = document.querySelector("#theme_change_button");
    const htmlElement = document.documentElement;

    // Function to apply the theme
    function applyTheme(theme) {
        htmlElement.classList.remove("light", "dark");
        htmlElement.classList.add(theme);
        localStorage.setItem("theme", theme);
        themeButton.querySelector("span").textContent = theme === "light" ? "Dark" : "Light";
    }

    // Get theme from localStorage or default to light
    let currentTheme = localStorage.getItem("theme") || "light";
    applyTheme(currentTheme);

    // Toggle theme on button click
    themeButton.addEventListener("click", function () {
        currentTheme = currentTheme === "light" ? "dark" : "light";
        applyTheme(currentTheme);
    });
});
