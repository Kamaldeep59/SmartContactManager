console.log('Hello from script.js');

let currentTheme = getTheme();
console.log(currentTheme);

//starting time
changeTheme();

function changeTheme() {
    
    //set to web page
    document.querySelector('html').classList.add(currentTheme);

    //set to listener to change these button
    const changeThemeButton = document.querySelector('#theme_change_button')
    changeThemeButton.addEventListener('click', (event) => {
        console.log('change theme button clicked');
        if (currentTheme === 'light') {
            currentTheme = 'dark';
        } else {
            currentTheme = 'light';
        }
        setTheme();
        document.querySelector('html').classList.remove('light');
        document.querySelector('html').classList.remove('dark');
        document.querySelector('html').classList.add(currentTheme);
       
    });

}

//set theme to global storage

function setTheme() {
    localStorage.setItem("theme", theme);
}


//get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    if (theme ) {
        return theme
    }
    else return "light"
}
